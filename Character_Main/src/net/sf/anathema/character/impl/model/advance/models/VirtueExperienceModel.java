package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.TraitCollectionUtilities;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public class VirtueExperienceModel extends AbstractIntegerValueModel {

  private final ICoreTraitConfiguration traitConfiguration;
  private final IPointCostCalculator calculator;

  public VirtueExperienceModel(ICoreTraitConfiguration traitConfiguration, IPointCostCalculator calculator) {
    super("Experience", "Virtues");
    this.traitConfiguration = traitConfiguration;
    this.calculator = calculator;
  }

  @Override
  public Integer getValue() {
    return getVirtueCosts();
  }

  private int getVirtueCosts() {
    int experienceCosts = 0;
    for (Trait virtue : TraitCollectionUtilities.getVirtues(traitConfiguration)) {
      experienceCosts += calculator.getVirtueCosts(virtue);
    }
    return experienceCosts;
  }
}