package net.bogor.itu.action.master;

import javax.inject.Inject;

import net.bogor.itu.entity.master.GroupPackage;
import net.bogor.itu.entity.master.InternetPackage;
import net.bogor.itu.service.master.GroupService;
import net.bogor.itu.service.master.PackageManagerService;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;

@Action(name = "/master/group")
@Results({
		@Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/master/group/group-form.ftl"),
		@Result(name = DefaultAction.INDEX, type = "freemarker", location = "/view/master/group/group-list.ftl") })
public class GroupAction extends DefaultAction implements
		ModelDriven<GroupActionModel> {

	private static final long serialVersionUID = 6037736932805968465L;

	private GroupActionModel model = new GroupActionModel();
	private ActionResult redirectToIndex = new ActionResult("redirect", "/master/group");
	
	@Inject
	private PackageManagerService packageService;
	
	@Inject
	private GroupService groupService;

	@Action
	public String packageList() {
		model.setGroups(groupService.findByKeyword(model.getQ(), model.getMax(),
				model.getPage() - 1));
		return DefaultAction.INDEX;
	}

	@Action(name = "/add", method = HttpMethod.GET)
	public String addForm() {
		model.setPackages(packageService.all());
		return DefaultAction.INPUT;
	}
	
	@Action(name = "/add", method = HttpMethod.POST)
	public ActionResult addService() {
		for (String s : model.getPackageIds()) {
			GroupPackage groupPackage = new GroupPackage();
			InternetPackage internetPackage = new InternetPackage();
			internetPackage.setId(s);
			groupPackage.setInternetPackage(internetPackage);
			groupPackage.setGroup(model.getGroup());
			model.getGroup().getGroupPackages().add(groupPackage);
		}
		groupService.save(model.getGroup());
		return redirectToIndex;
	}
	
	@Override
	public GroupActionModel getModel() {
		return model;
	}

}