package net.sf.anathema.character.equipment.creation.model;

import net.sf.anathema.character.equipment.creation.presenter.IIntValueModel;
import net.sf.anathema.character.equipment.creation.presenter.IRangedCombatStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.IWeaponTag;
import net.sf.anathema.character.equipment.creation.presenter.IWeaponTagsModel;
import net.sf.anathema.character.equipment.creation.presenter.RangedIntValueModel;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.data.Range;

public class RangedWeaponStatisticsModel extends OffensiveStatisticsModel implements IRangedCombatStatisticsModel {

  private final IIntValueModel rangeModel = new RangedIntValueModel(new Range(1, Integer.MAX_VALUE), 1);
  private final IWeaponTagsModel weaponTagsModel;

  public RangedWeaponStatisticsModel(IWeaponTagsModel weaponTagsModel) {
    this.weaponTagsModel = weaponTagsModel;
    for (IWeaponTag tag : weaponTagsModel.getAllTags()) {
      weaponTagsModel.getSelectedModel(tag).addChangeListener(new IBooleanValueChangedListener() {
        @Override
        public void valueChanged(boolean selected) {
          RangedWeaponStatisticsModel.this.announceValidationChange();
        }
      });
    }
  }

  @Override
  public IIntValueModel getRangeModel() {
    return rangeModel;
  }

  @Override
  public boolean isValid() {
    return super.isValid() && isRangedWeaponValid();
  }

  @SuppressWarnings("RedundantIfStatement")
  private boolean isRangedWeaponValid() {
    if (!weaponTagsModel.isThrownTypeTagSelected() && weaponTagsModel.isThrownWeaponTagSelected()) {
      return false;
    }
    if (weaponTagsModel.isRangedTypeTagSelected()) {
      return true;
    }
    return false;
  }
}