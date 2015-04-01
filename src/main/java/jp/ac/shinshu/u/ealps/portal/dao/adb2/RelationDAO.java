/**
 * 
 */
package jp.ac.shinshu.u.ealps.portal.dao.adb2;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.ac.shinshu.u.common.JDBCUtility;
import jp.ac.shinshu.u.ealps.portal.bean.RelationCourseBean;

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

	public List<RelationCourseBean> selectRelationCourseBeanList(String userId) {
		
		List<RelationCourseBean> relationCourseBeanList = new ArrayList<RelationCourseBean>();

		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ");
		sql.append("a.*, ");
		sql.append("b.titleCode, b.titleName, b.url, ");
		sql.append("tr.*, ");
		sql.append("trData.name ");
		sql.append("FROM ");
		sql.append("relation a, courseData b, relation tr, accountData trData ");
		sql.append("WHERE a.uid = ? ");
		sql.append("AND b.dataFrom = 0 ");
		sql.append("AND a.listId = b.listId ");
		sql.append("AND b.listId = tr.listId ");
		sql.append("AND tr.roleId = 1 ");
		sql.append("AND tr.uid = trData.uid ");
		sql.append("AND trData.dataFrom = 0 ");
		
		resultSet = find(sql.toString(), userId);

		try {
			RelationCourseBean relationCourseBean = null;
			while(resultSet.next()) {
				relationCourseBean = new RelationCourseBean();
				relationCourseBean.setCourseTitleCode(resultSet.getString("titleCode"));
				relationCourseBean.setCourseTitleName(resultSet.getString("titleName"));
				relationCourseBean.setCourseURL(resultSet.getString("url"));
				relationCourseBean.setTeacherName(resultSet.getString("name"));

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
	
}
