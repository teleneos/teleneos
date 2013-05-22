package org.teleneos.ticket.answer;


import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

public class PremadeAnswerActionModel extends DefaultActionModel {
	private PremadeAnswer answer = new PremadeAnswer();
	private EntityListWrapper<PremadeAnswer> answers = new EntityListWrapper<PremadeAnswer>();

	public PremadeAnswer getAnswer() {
		return answer;
	}

	public void setAnswer(PremadeAnswer answer) {
		this.answer = answer;
	}

	public EntityListWrapper<PremadeAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(EntityListWrapper<PremadeAnswer> answers) {
		this.answers = answers;
	}

}
