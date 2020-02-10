package vira.alemamah.ModelSubjectBehavior;

public class ModelSubjectBehavior {
    String category;
    String type;
    String text;
    boolean subTitle=false;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isSubTitle() {
        return subTitle;
    }

    public void setSubTitle(boolean subTitle) {
        this.subTitle = subTitle;
    }
}
