/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.service;

import java.util.List;

import jp.ac.shinshu.u.ealps.portal.bean.RelationCourseBean;
import jp.ac.shinshu.u.ealps.portal.dao.adb2.RelationDAO;

/**
 * @author Osamu HASEGAWA
 *
 */
public class RelationCourseService implements IRelationCourseService {

	/**
	 * <p>デフォルトコンストラクタ</p>
	 */
	public RelationCourseService() {

	}

	@Override
	public List<RelationCourseBean> getRelationCourseBeanList(String uid) {
		List<RelationCourseBean> relationCourseBeanList;
		RelationDAO relationCourseDAO = new RelationDAO();
		relationCourseBeanList = relationCourseDAO.selectRelationCourseBeanList(uid);
		return relationCourseBeanList;
	}

}
