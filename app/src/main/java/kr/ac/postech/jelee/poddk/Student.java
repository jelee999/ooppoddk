package kr.ac.postech.jelee.poddk;



public class Student {
    String StudentIDdata;
    int ImageID;
    String Name;
    int Age;
    String Sex;
    String majorSubject;
    String minorSubject;
    String learnContents;
    String studentCapability;
    String availableTime;
    String etcData = "";

    public Student(String studentIDData, int imageID, String name, int age, String sex, String majorsubject, String minorsubject,
                   String learncontents, String studentcapability, String availabletime, String etcdata) {
        StudentIDdata = studentIDData;
        ImageID = imageID;
        Name = name;
        Age = age;
        Sex = sex;
        majorSubject = majorsubject;
        minorSubject = minorsubject;
        learnContents = learncontents;
        studentCapability = studentcapability;
        availableTime = availabletime;
        etcData = etcdata;

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

    public String getmajorSubject() {return majorSubject; }
    public void setmajorSubject(String subject) {majorSubject = subject;}

    public String getminorSubject() {return minorSubject; }
    public void setminorSubject(String contents) {minorSubject = contents;}

    public String getlearnContents() {return learnContents; }
    public void setlearnContents(String contents) {learnContents = contents;}

    public String getstudentCapability() {return studentCapability; }
    public void setstudentCapability(String contents) {studentCapability = contents;}

    public String getavailableTime() {return availableTime; }
    public void setavailableTime(String contents) {availableTime = contents;}

    public String getetcData() {return etcData; }
    public void setetcData(String contents) {etcData = contents;}

}