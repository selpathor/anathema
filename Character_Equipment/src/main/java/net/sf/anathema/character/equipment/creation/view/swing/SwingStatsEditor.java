package net.sf.anathema.character.equipment.creation.view.swing;

import net.sf.anathema.character.equipment.creation.presenter.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.item.model.ModelToStats;
import net.sf.anathema.character.equipment.item.model.NullClosure;
import net.sf.anathema.character.equipment.item.model.StatsEditor;
import net.sf.anathema.equipment.editor.wizard.AnathemaWizardDialog;
import net.sf.anathema.equipment.editor.wizard.IAnathemaWizardPage;
import net.sf.anathema.equipment.editor.wizard.WizardDialog;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.lib.gui.dialog.core.DialogResult;
import net.sf.anathema.lib.gui.dialog.userdialog.DialogCloseHandler;
import net.sf.anathema.lib.util.Closure;

import javax.swing.SwingUtilities;

public class SwingStatsEditor implements StatsEditor {

  private Closure<IEquipmentStats> whenChangesAreFinished = new NullClosure<>();
  private final ModelToStats modelToStats = new ModelToStats();

  @Override
  public void editStats(Resources resources, IEquipmentStatisticsCreationModel model) {
    doIt(resources, model);
  }

  private void doIt(Resources resources, IEquipmentStatisticsCreationModel model) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        runDialog(resources, model);
      }
    });
  }

  @Override
  public void whenChangesAreConfirmed(Closure<IEquipmentStats> action) {
    this.whenChangesAreFinished = action;
  }

  private void runDialog(Resources resources, final IEquipmentStatisticsCreationModel model) {
    IEquipmentStatisticsCreationViewFactory viewFactory = new EquipmentStatisticsCreationViewFactory();
    IAnathemaWizardPage startPage = chooseStartPage(resources, model, viewFactory);
    WizardDialog dialog = new AnathemaWizardDialog(SwingApplicationFrame.getParentComponent(), startPage);
    dialog.show(new CreateStatsHandler(model));
  }

  private IAnathemaWizardPage chooseStartPage(Resources resources, IEquipmentStatisticsCreationModel model,
                                              IEquipmentStatisticsCreationViewFactory viewFactory) {
    switch (model.getEquipmentType()) {
      case CloseCombat:
        return new CloseCombatStatisticsPresenterPage(resources, model, viewFactory);
      case RangedCombat:
        return new RangedCombatStatisticsPresenterPage(resources, model, viewFactory);
      case Armor:
        return new ArmourStatisticsPresenterPage(resources, model, viewFactory);
      case Artifact:
        return new ArtifactStatisticsPresenterPage(resources, model, viewFactory);
      case TraitModifying:
        return new TraitModifyingStatisticsPresenterPage(resources, model, viewFactory);
      default:
        throw new IllegalArgumentException("Type must be defined to edit.");
    }
  }

  private class CreateStatsHandler implements DialogCloseHandler {
    private final IEquipmentStatisticsCreationModel model;

    public CreateStatsHandler(IEquipmentStatisticsCreationModel model) {
      this.model = model;
    }

    @Override
    public void handleDialogClose(DialogResult result) {
      if (result.isCanceled()) {
        return;
      }
      whenChangesAreFinished.execute(modelToStats.createStats(model));
    }
  }
}