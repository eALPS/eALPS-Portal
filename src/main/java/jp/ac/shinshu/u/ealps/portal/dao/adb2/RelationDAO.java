/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.dao.adb2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.ac.shinshu.u.common.JDBCUtility;
import jp.ac.shinshu.u.ealps.portal.bean.RelationCourseBean;
import jp.ac.shinshu.u.ealps.portal.entity.AccountData;
import jp.ac.shinshu.u.ealps.portal.entity.CourseData;
import jp.ac.shinshu.u.ealps.portal.entity.OpInfo;
import jp.ac.shinshu.u.ealps.portal.entity.Relation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>履修・担当テーブル用のDAO</p>
 *
 * @version 1.0.0
 * @author Osamu HASEGAWA
 *
 */
public class RelationDAO extends ADB2AbstractDAO {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 *
	 */
	public RelationDAO() {
		super();
	}

public List<RelationCourseBean> selectRelationCourseBeanList(String uid) {

		List<RelationCourseBean> relationCourseBeanList = new ArrayList<RelationCourseBean>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("a.relId, a.listId, a.chain, a.uid, a.roleId, a.enable, a.updateAt, ");
		sql.append("b.rowId, b.uid, b.titleCode, b.titleName, b.regCode, b.depCode, b.siteUid, b.opFlag, b.opYear, b.opLec, b.toLMS, b.listId, b.delFlag, b.mdlId, b.mdlCatId, b.memo, b.originalDate, b.updateDate, b.catId, b.url, b.optUrl, b.dataFrom ");
		//sql.append("c.opinfoId, c.courseId, c.opLec, c.opWday, c.opHour, c.campus ");
		sql.append("FROM ");
		sql.append("relation a, courseData b ");
		sql.append("WHERE a.uid = ? ");
		sql.append("AND a.enable = 1 ");
		sql.append("AND b.dataFrom = 0 ");
		sql.append("AND NOT b.toLMS = 0 ");
		sql.append("AND b.delFlag = 0 ");
//		sql.append("AND b.opFlag = 0 ");
		sql.append("AND a.listId = b.listId ");
//		sql.append("AND b.rowId = c.courseId ");
		sql.append("ORDER BY b.titleCode, b.opLec ");
//		sql.append("ORDER BY b.titleCode, c.opLec, c.opWday, c.opHour ");
		
		StringBuilder sql2 = new StringBuilder();
		sql2.append("SELECT ");
		sql2.append("a.relId, a.listId, a.chain, a.uid, a.roleId, a.enable, a.updateAt, ");
		sql2.append("b.rowId, b.uid, b.id, b.uidNumber, b.password, b.affili, b.name, b.FirstName, b.lastName, b.mail, b.subMail, b.facultyNumber, b.enable, b.updateDate, b.dataFrom, b.memo ");
		sql2.append("FROM ");
		sql2.append("relation a, accountData b ");
		sql2.append("WHERE a.listId = ? ");
		sql2.append("AND a.roleId = ? ");
		sql2.append("AND a.enable = 1 ");
		sql2.append("AND a.uid = b.uid ");
		sql2.append("AND b.dataFrom = 0 ");
		sql2.append("AND b.enable = 1 ");
		
		StringBuilder sql3 = new StringBuilder();
		sql3.append("SELECT ");
		sql3.append("a.opinfoId, a.courseId, a.opLec, a.opWday, a.opHour, a.campus ");
		sql3.append("FROM ");
		sql3.append("opinfo a ");
		sql3.append("WHERE a.courseId = ? ");

		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		
		List<AccountData> teacherList = null;
		AccountData accountData = null;
		List<OpInfo> opInfoList = null;
		OpInfo opInfo = null;
		
		resultSet = find(sql.toString(), uid);
		try {
			RelationCourseBean relationCourseBean = null;
			Relation relation = null;
			CourseData courseData = null;
			while(resultSet.next()) {
				relationCourseBean = new RelationCourseBean();

				relation = new Relation();
				relation.setRelId(resultSet.getInt("a.relId"));
				relation.setListId(resultSet.getInt("a.listId"));
				relation.setChain(resultSet.getString("a.chain"));
				relation.setUid(resultSet.getString("a.uid"));
				relation.setRoleId(resultSet.getInt("a.roleId"));
				relation.setEnable(resultSet.getInt("a.enable"));
//				relation.setUpdateAt(resultSet.getTimestamp("updateAt"));
				relationCourseBean.setRelation(relation);

				courseData = new CourseData();
				courseData.setRowId(resultSet.getInt("b.rowId"));
				courseData.setUid(resultSet.getString("b.uid"));
				courseData.setTitleCode(resultSet.getString("b.titleCode"));
				courseData.setTitleName(resultSet.getString("b.titleName"));
				courseData.setOpYear(resultSet.getInt("b.opYear"));
				courseData.setOpLec(resultSet.getString("b.opLec"));
				courseData.setListId(resultSet.getInt("b.listId"));
				courseData.setUrl(resultSet.getString("b.url"));
				relationCourseBean.setCourseData(courseData);
				
				// 主担当取得
				resultSet2 = find(sql2.toString(), courseData.getListId(), 1);
				teacherList = new ArrayList<AccountData>();
				accountData = null;
				while(resultSet2.next()) {
					accountData = new AccountData();
					accountData.setName(resultSet2.getString("b.name"));
					accountData.setFirstName(resultSet2.getString("b.FirstName"));
					accountData.setLastName(resultSet2.getString("b.lastName"));
					accountData.setMail(resultSet2.getString("b.mail"));
					teacherList.add(accountData);
				}
				relationCourseBean.setTeacherList(teacherList);
				
				// 副担当取得
				resultSet2 = find(sql2.toString(), courseData.getListId(), 2);
				teacherList = new ArrayList<AccountData>();
				accountData = null;
				while(resultSet2.next()) {
					accountData = new AccountData();
					accountData.setName(resultSet2.getString("b.name"));
					accountData.setFirstName(resultSet2.getString("b.FirstName"));
					accountData.setLastName(resultSet2.getString("b.lastName"));
					accountData.setMail(resultSet2.getString("b.mail"));
					teacherList.add(accountData);
				}
				relationCourseBean.setSubTeacherList(teacherList);
				
				// 開講曜日・時限
				resultSet2 = find(sql3.toString(), courseData.getRowId());
				opInfoList = new ArrayList<OpInfo>();
				while(resultSet2.next()) {
					opInfo = new OpInfo();
					opInfo.setOpinfoId(resultSet2.getInt("a.opinfoId"));
					opInfo.setCourseId(resultSet2.getInt("a.courseId"));
					opInfo.setOpLec(resultSet2.getString("a.opLec"));
					opInfo.setOpWday(resultSet2.getString("a.opWday"));
					opInfo.setOpHour(resultSet2.getString("a.opHour"));
					opInfo.setCampus(resultSet2.getInt("a.campus"));
					opInfoList.add(opInfo);
				}
				relationCourseBean.setOpInfoList(opInfoList);

				relationCourseBeanList.add(relationCourseBean);
			}
		} catch (SQLException e) {
			logger.error("SQLException", e);
			throw new RuntimeException(e);
		} finally {
			JDBCUtility.close(statement, resultSet);
		}

		return relationCourseBeanList;
	}

//	public List<RelationCourseBean> selectRelationCourseBeanList(String userId) {
//
//		List<RelationCourseBean> relationCourseBeanList = new ArrayList<RelationCourseBean>();
//
//		StringBuilder sql = new StringBuilder();
//
//		sql.append("SELECT ");
//		sql.append("a.*, ");
//		sql.append("b.titleCode, b.titleName, b.url, ");
//		sql.append("tr.*, ");
//		sql.append("trData.name ");
//		sql.append("FROM ");
//		sql.append("relation a, courseData b, relation tr, accountData trData ");
//		sql.append("WHERE a.uid = ? ");
//		sql.append("AND b.dataFrom = 0 ");
//		sql.append("AND a.listId = b.listId ");
//		sql.append("AND b.listId = tr.listId ");
//		sql.append("AND tr.roleId = 1 ");
//		sql.append("AND tr.uid = trData.uid ");
//		sql.append("AND trData.dataFrom = 0 ");
//
//		resultSet = find(sql.toString(), userId);
//
//		try {
//			RelationCourseBean relationCourseBean = null;
//			while(resultSet.next()) {
//				relationCourseBean = new RelationCourseBean();
//				relationCourseBean.setCourseTitleCode(resultSet.getString("titleCode"));
//				relationCourseBean.setCourseTitleName(resultSet.getString("titleName"));
//				relationCourseBean.setCourseURL(resultSet.getString("url"));
//				relationCourseBean.setTeacherName(resultSet.getString("name"));
//
//				relationCourseBeanList.add(relationCourseBean);
//			}
//		} catch (SQLException e) {
//			logger.error("SQLException", e);
//			throw new RuntimeException(e);
//		} finally {
//			JDBCUtility.close(statement, resultSet);
//		}
//
//		return relationCourseBeanList;
//	}

}
