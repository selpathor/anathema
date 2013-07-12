package net.sf.anathema.swing.hero.perspective;

import net.sf.anathema.hero.advance.experience.ExperiencePointManagement;
import net.sf.anathema.hero.advance.experience.HardCodedExperiencePointManagement;
import net.sf.anathema.hero.advance.creation.IBonusPointManagement;
import net.sf.anathema.hero.advance.creation.BonusPointManagement;
import net.sf.anathema.character.main.presenter.CharacterPresenter;
import net.sf.anathema.character.main.presenter.OverviewPresenter;
import net.sf.anathema.character.main.view.CharacterView;
import net.sf.anathema.character.main.view.OverviewContainer;
import net.sf.anathema.character.main.view.SubViewMap;
import net.sf.anathema.character.main.view.SubViewRegistry;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

public class CharacterViewFactory {
  private final Resources resources;
  private final IApplicationModel model;

  public CharacterViewFactory(Resources resources, IApplicationModel model) {
    this.resources = resources;
    this.model = model;
  }

  public IView createView(Item item) {
    Hero hero = (Hero) item.getItemData();
    SubViewRegistry viewFactory = new SubViewMap(model.getObjectFactory());
    CharacterView characterView = new TaskedCharacterView(viewFactory);
    new CharacterPresenter(hero, characterView, resources, model).initPresentation();
    initOverviewPresentation(hero, characterView, resources);
    item.getChangeManagement().setClean();
    return characterView;
  }

  private void initOverviewPresentation(Hero hero, OverviewContainer container, Resources resources) {
    IBonusPointManagement bonusPointManagement = new BonusPointManagement(hero);
    ExperiencePointManagement experiencePointManagement = new HardCodedExperiencePointManagement(hero);
    new OverviewPresenter(resources, hero, container, bonusPointManagement, experiencePointManagement).initPresentation();
  }
}