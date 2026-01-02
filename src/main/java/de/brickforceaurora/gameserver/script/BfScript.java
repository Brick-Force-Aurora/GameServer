package de.brickforceaurora.gameserver.script;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BfScript {

    private String alias;
    private boolean enableOnAwake;
    private boolean visibleOnAwake;
    private List<ScriptCmd> cmdList;

    /* ===================== PROPERTIES ===================== */

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public boolean isEnableOnAwake() {
        return enableOnAwake;
    }

    public void setEnableOnAwake(boolean enableOnAwake) {
        this.enableOnAwake = enableOnAwake;
    }

    public boolean isVisibleOnAwake() {
        return visibleOnAwake;
    }

    public void setVisibleOnAwake(boolean visibleOnAwake) {
        this.visibleOnAwake = visibleOnAwake;
    }

    public List<ScriptCmd> getCmdList() {
        return cmdList;
    }

    /* ===================== CONSTRUCTOR ===================== */

    public BfScript(
            String alias,
            boolean enableOnAwake,
            boolean visibleOnAwake,
            String cmdListStr
    ) {
        if (alias != null) {
            alias = alias.trim();
        }

        this.alias = alias;
        this.enableOnAwake = enableOnAwake;
        this.visibleOnAwake = visibleOnAwake;
        this.cmdList = new ArrayList<>();

        if (cmdListStr != null && !cmdListStr.isEmpty()) {
            String[] array = cmdListStr.split(
                    Arrays.toString(ScriptCmd.CMD_DELIMITERS)
            );

            if (array != null) {
                for (String s : array) {
                    // ScriptCmd item = ScriptCmdFactory.create(s);
                    // cmdList.add(item);
                }
            }
        }
    }

    /* ===================== METHODS ===================== */

    public String getCommandString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < cmdList.size(); i++) {
            sb.append(cmdList.get(i).getDescription());
            if (i < cmdList.size() - 1) {
                sb.append(ScriptCmd.CMD_DELIMITERS[0]);
            }
        }

        return sb.toString();
    }
}
