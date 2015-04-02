/**
 * 
 */
package jp.ac.shinshu.u.ealps.portal.service;

import java.util.List;

import jp.ac.shinshu.u.ealps.portal.bean.RelationCourseBean;

import com.google.inject.ImplementedBy;

/**
 * @author Osamu HASEGAWA
 *
 */
@ImplementedBy(RelationCourseService.class)
public interface IRelationCourseService {
	
	public List<RelationCourseBean> getRelationCourseBeanList(String userId);
	
	public List<List<List<RelationCourseBean>>> getCourseScheduleList(List<RelationCourseBean> relationCourseBeanList);

}
