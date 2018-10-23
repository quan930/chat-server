package app.mrquan.pojo;

public class Function {
    private String function;
    private String description;//描述

    @Override
    public String toString() {
        return "功能:" + function + "\t\t描述:" + description;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
