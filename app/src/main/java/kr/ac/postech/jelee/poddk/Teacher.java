package kr.ac.postech.jelee.poddk;



public class Teacher {
    String TeacherIDdata;
    int ImageID;
    String Name;
    int Age;
    String Sex;
    String majorSubject;
    String minorSubject;
    String teachContents;
    String teacherCapability;
    String availableTime;
    String etcData = "";

    public Teacher(String studentIDData, int imageID, String name, int age, String sex, String majorsubject, String minorsubject,
                   String learncontents, String studentcapability, String availabletime, String etcdata) {
        TeacherIDdata = studentIDData;
        ImageID = imageID;
        Name = name;
        Age = age;
        Sex = sex;
        majorSubject = majorsubject;
        minorSubject = minorsubject;
        teachContents = learncontents;
        teacherCapability = studentcapability;
        availableTime = availabletime;
        etcData = etcdata;

    }

    public String getTeacherIDdata() {return TeacherIDdata; }
    public void setTeacherIDdata(String studentIDdata) {TeacherIDdata = studentIDdata;}

    public String getName() {return Name; }
    public void setName(String name) {Name = name;}

    public int getImageID() {return ImageID; }
    public void setImageID(int imageid) {ImageID=imageid;}

    public int getAge() {return Age; }
    public void setAge(int age) {Age = age;}

    public String getSex() {return Sex; }
    public void setSex(String sex) {Sex = sex;}

    public String getmajorSubject() {return majorSubject; }
    public void setmajorSubject(String subject) {majorSubject = subject;}

    public String getminorSubject() {return minorSubject; }
    public void setminorSubject(String contents) {minorSubject = contents;}

    public String getteachContents() {return teachContents; }
    public void setteachContents(String contents) {teachContents = contents;}

    public String getteacherCapability() {return teacherCapability; }
    public void setteacherCapability(String contents) {teacherCapability = contents;}

    public String getavailableTime() {return availableTime; }
    public void setavailableTime(String contents) {availableTime = contents;}

    public String getetcData() {return etcData; }
    public void setetcData(String contents) {etcData = contents;}

}