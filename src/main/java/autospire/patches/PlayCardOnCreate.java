package autospire.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

public class PlayCardOnCreate {
    @SpirePatch(clz=ShowCardAndAddToHandEffect.class, method=SpirePatch.CONSTRUCTOR, paramtypez={AbstractCard.class, float.class, float.class})
    public static class ConstructorOne {
        public static void Postfix(ShowCardAndAddToHandEffect __instance) {
            playCard(__instance);
        }
    }

    @SpirePatch(clz=ShowCardAndAddToHandEffect.class, method=SpirePatch.CONSTRUCTOR, paramtypez={AbstractCard.class})
    public static class ConstructorTwo {
        public static void Postfix(ShowCardAndAddToHandEffect __instance) {
            playCard(__instance);
        }
    }

    public static void playCard(ShowCardAndAddToHandEffect action) {
        AbstractCard c = ReflectionHacks.getPrivate(action, ShowCardAndAddToHandEffect.class, "card");
        c.freeToPlayOnce = true;
        AbstractDungeon.actionManager.cardQueue.add(0, new CardQueueItem(c, AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng)));
    }
}