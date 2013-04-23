package net.bogor.itu.action.pos;

import javax.inject.Inject;

import net.bogor.itu.service.pos.ConversionService;
import net.bogor.itu.service.pos.UnitOfMeasureService;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;

@Action(name = "/pos/conversion")
@Results({ @Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/pos/conversion/conversion-form.ftl") })
public class ConversionAction extends DefaultAction implements
		ModelDriven<ConversionActionModel> {

	private static final long serialVersionUID = -401110166169568598L;

	private ConversionActionModel model = new ConversionActionModel();

	@Inject
	private UnitOfMeasureService uomService;
	@Inject
	private ConversionService conversionService;

	private ActionResult redirectToIndex = new ActionResult("redirect",
			"/pos/conversion");

	@Action
	public ActionResult conversionList() {
		model.setConversions(conversionService.findAll(model.getMax(),
				model.getPage() - 1));
		return new ActionResult("freemarker",
				"/view/pos/conversion/conversion-list.ftl");
	}

	@Action(name = "/add", method = HttpMethod.GET)
	public String addForm() {
		model.setUoms(uomService.findByKeyword("", null, null, 0, 0));
		return DefaultAction.INPUT;
	}

	@Action(name = "/add", method = HttpMethod.POST)
	public ActionResult add() {
		conversionService.save(model.getConversion());
		return redirectToIndex;
	}

	@Override
	public ConversionActionModel getModel() {
		return model;
	}

}
