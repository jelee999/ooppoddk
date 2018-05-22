package kr.ac.postech.jelee.poddk;



public class Student {
    String StudentIDdata;
    int ImageID;
    String Name;
    int Age;
    String Sex;
    String Subject;

    public Student(String studentIDData, int imageID, String name, int age, String sex, String subject ) {
        StudentIDdata = studentIDData;
        ImageID = imageID;
        Name = name;
        Age = age;
        Sex = sex;
        Subject = subject;
    }

    public String getStudentIDdata() {return StudentIDdata; }
    public void setStudentIDdata(String studentIDdata) {StudentIDdata = studentIDdata;}

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