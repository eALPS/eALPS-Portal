package jp.ac.shinshu.u.ealps.portal.panel;

import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 * @author Osamu HASEGAWA
 *
 */
public class AttentionPanel extends Panel {

	private static final long serialVersionUID = 8158419776238997245L;

	/**
	 * @param id panelId
	 */
	public AttentionPanel(String id) {
		super(id);
		draw();
	}

	private void draw() {

		//IModel href = new Modle<String>("https://docs.google.com/forms/d/e/1FAIpQLSdnJaVW-GgY5dpNeFg0uDJMIRurtPyK-wCCWZuOa8L6fl9Njg/viewform");

		add(new ExternalLink("eLearningCenterForm", Model.of("https://docs.google.com/forms/d/e/1FAIpQLSdnJaVW-GgY5dpNeFg0uDJMIRurtPyK-wCCWZuOa8L6fl9Njg/viewform"), Model.of("e-Learningセンター")));

	}

}
