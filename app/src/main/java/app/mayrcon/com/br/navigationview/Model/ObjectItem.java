package app.mayrcon.com.br.navigationview.Model;

public class ObjectItem {



    public int id;
    public String title;
    public String desc;

    public ObjectItem() {

    }
    public String toString(){
        return (this.getDesc());
    }
    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




}
