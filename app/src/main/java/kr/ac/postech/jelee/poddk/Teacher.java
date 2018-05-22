package kr.ac.postech.jelee.poddk;



public class Teacher {
    String TeacherIDdata;
    int ImageID;
    String Name;
    int Age;
    String Sex;
    String Subject;

    public Teacher(String teacherIDData, int imageID, String name, int age, String sex, String subject ) {
        TeacherIDdata = teacherIDData;
        ImageID = imageID;
        Name = name;
        Age = age;
        Sex = sex;
        Subject = subject;
    }

    public String getTeacherIDdata() {return TeacherIDdata; }
    public void setTeacherIDdata(String teacherIDdata) {TeacherIDdata = teacherIDdata;}

    public String getName() {return Name; }
    public void setName(String name) {Name = name;}

    public int getImageID() {return ImageID; }
    public void setImageID(int imageid) {ImageID=imageid;}

    public int getAge() {return Age; }
    public void setAge(int age) {Age = age;}

    public String getSex() {return Sex; }
    public void setSex(String sex) {Sex = sex;}

    public String getSubject() {return Subject; }
    public void setSubject(String subject) {Subject = subject;}

}