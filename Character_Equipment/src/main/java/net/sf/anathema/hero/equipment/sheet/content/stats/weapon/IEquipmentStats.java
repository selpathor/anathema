package net.sf.anathema.hero.equipment.sheet.content.stats.weapon;

import net.sf.anathema.character.framework.library.IStats;
import net.sf.anathema.lib.util.Identifier;

public interface IEquipmentStats extends IStats, Identifier {
  boolean representsItemForUseInCombat();
}