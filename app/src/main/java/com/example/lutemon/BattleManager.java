package com.example.lutemon;

import android.os.Handler;

import com.example.lutemon.fragments.BattleField;
import com.example.lutemon.lutemons.Lutemon;
import com.example.lutemon.lutemons.LutemonStorage;

import java.util.ArrayList;

public class BattleManager {
    private final Handler handler = new Handler();
    private ArrayList<Lutemon> selectFighters;
    private int turnIndex = 0;
    private int logNr = 1;
    private BattleField battleField;

    public BattleManager(BattleField battleField) {
        this.battleField = battleField;
    }

    public void setFighters(ArrayList<Lutemon> fighters) {
        this.selectFighters = fighters;
    }

    public void startBattle() {
        handler.postDelayed(this::battleTurn, 2000);
    }

    private void battleTurn() {
        Lutemon attacker = selectFighters.get(turnIndex);
        Lutemon defender = selectFighters.get(1 - turnIndex);
        battleField.flipSword(turnIndex);
        battleField.logBattle(attacker, defender, logNr);
        defender.defendAgainst(attacker);
        battleField.logAttack(attacker, defender);
        if (defender.getHealth() <= 0) {
            battleField.logDefenderKilled(defender);
            LutemonStorage.getInstance().kill(defender);
            attacker.heal();
            attacker.train();
        } else {
            battleField.logDefenderSurvived(defender);
            turnIndex = 1 - turnIndex;
            logNr = 3 - logNr;
            startBattle();
        }
    }

    public void stopBattle() {
        handler.removeCallbacksAndMessages(null);
    }
}
