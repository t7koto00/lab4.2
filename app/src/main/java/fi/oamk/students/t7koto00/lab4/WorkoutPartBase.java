package fi.oamk.students.t7koto00.lab4;

import java.io.Serializable;

public abstract class WorkoutPartBase implements Serializable {
    abstract public String getName();
    abstract public int getLength();
    abstract public void setLength(int length);
}
