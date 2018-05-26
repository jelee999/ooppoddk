package kr.ac.postech.jelee.poddk;


import android.os.Parcel;
import android.os.Parcelable;


public class Person implements Parcelable {
    private String Identification;
    private String IDdata;
    private int ImageID;
    private String Name;
    private int Age;
    private String Sex;

    private String majorSubject;
    private String minorSubject;

    private String Contents;
    private String Ability;
    private String availableTime;
    private String etcData = "";

    public Person(String IDData, int imageID, String name, int age, String sex, String majorsubject, String minorsubject,
                  String contents, String ability, String availabletime, String etcdata) {
        Identification = IDData + minorsubject;
        IDdata = IDData;
        ImageID = imageID;
        Name = name;
        Age = age;
        Sex = sex;
        majorSubject = majorsubject;
        minorSubject = minorsubject;
        Contents = contents;
        Ability = ability;
        availableTime = availabletime;
        etcData = etcdata;

    }

    public String getIDdata() {return IDdata; }
    public void setIDdata(String IDdata) {IDdata = IDdata;}

    public String getIdentification() {return Identification; }
    public void setIdentification(String id) {Identification = id;}

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

    public String getContents() {return Contents; }
    public void setContents(String contents) {Contents = contents;}

    public String getAbility() {return Ability; }
    public void setAbility(String contents) {Ability = contents;}

    public String getavailableTime() {return availableTime; }
    public void setavailableTime(String contents) {availableTime = contents;}

    public String getetcData() {return etcData; }
    public void setetcData(String contents) {etcData = contents;}

    public Person(Parcel in){
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Identification);
        dest.writeString(IDdata);
        dest.writeInt(ImageID);
        dest.writeString(Name);
        dest.writeInt(Age);
        dest.writeString(Sex);
        dest.writeString(majorSubject);
        dest.writeString(minorSubject);
        dest.writeString(Contents);
        dest.writeString(Ability);
        dest.writeString(availableTime);
        dest.writeString(etcData);
    }

    private void readFromParcel(Parcel in) {
        Identification = in.readString();
        IDdata = in.readString();
        ImageID = in.readInt();
        Name = in.readString();
        Age = in.readInt();
        Sex = in.readString();
        majorSubject = in.readString();
        minorSubject = in.readString();
        Contents = in.readString();
        Ability = in.readString();
        availableTime = in.readString();
        etcData = in.readString();
    }

    public static final Creator CREATOR = new Creator() {
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

}