/**
 *
 */
package jp.ac.shinshu.u.common.expansion;

import java.util.List;

import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.ajax.markup.html.AjaxIndicatorAppender;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;

/**
 * @author Osamu HASEGAWA
 * @param <T>
 *
 */
public class IndicatingAjaxDropDownChoice<T> extends DropDownChoice<T> implements IAjaxIndicatorAware {

	private static final long serialVersionUID = -1136447592120320679L;
	private final AjaxIndicatorAppender indicatorAppender = new AjaxIndicatorAppender();

	public IndicatingAjaxDropDownChoice(String id, IModel<T> model, IModel<? extends List<? extends T>> choices) {
		super(id, model, choices);
		add(new Behavior[] {
			this.indicatorAppender
		});
	}

	/**
	 * @see org.apache.wicket.ajax.IAjaxIndicatorAware#getAjaxIndicatorMarkupId()
	 */
	@Override
	public String getAjaxIndicatorMarkupId() {
		return indicatorAppender.getMarkupId();
	}

//	protected boolean wantOnSelectionChangedNotifications() {
//		return true;
//	}

}
