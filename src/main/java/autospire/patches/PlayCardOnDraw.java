package autospire.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch(clz=DrawCardAction.class, method="update")
public class PlayCardOnDraw {
    public static void Replace(DrawCardAction __instance) {
        __instance.isDone = true;
        for (int i = 0; i < __instance.amount; i++)
            AbstractDungeon.actionManager.addToTop(new PlayTopCardAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng), false));
    }
}