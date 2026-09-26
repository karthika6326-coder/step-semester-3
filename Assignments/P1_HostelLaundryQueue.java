interface WashType {
    String getName();
    int getDuration();
    double getCharge();
}

class QuickWash implements WashType {
    public String getName() {
        return "Quick";
    }

    public int getDuration() {
        return 30;
    }

    public double getCharge() {
        return 20;
    }
}

class NormalWash implements WashType {
    public String getName() {
        return "Normal";
    }

    public int getDuration() {
        return 45;
    }

    public double getCharge() {
        return 30;
    }
}

class HeavyWash implements WashType {
    public String getName() {
        return "Heavy";
    }

    public int getDuration() {
        return 60;
    }

    public double getCharge() {
        return 45;
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class WashCycle {
    private Student student;
    private WashingMachine machine;
    private WashType washType;

    public WashCycle(Student student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public void start() {
        System.out.printf("%s wash started on %s for %s (%d min). Charge: ₹%.2f.%n",
                washType.getName(),
                machine.getMachineId(),
                student.getName(),
                washType.getDuration(),
                washType.getCharge());
    }

    public void complete() {
        System.out.println(machine.getMachineId() + " cycle completed.");
        machine.completeWash();
    }
}

class WashingMachine {
    private String machineId;
    private WashCycle currentCycle;

    public WashingMachine(String machineId) {
        this.machineId = machineId;
    }

    public String getMachineId() {
        return machineId;
    }

    public boolean isFree() {
        return currentCycle == null;
    }

    public boolean startWash(Student student, WashType washType) {
        if (!isFree()) {
            System.out.println("Machine " + machineId + " is currently busy.");
            return false;
        }

        currentCycle = new WashCycle(student, this, washType);
        currentCycle.start();
        return true;
    }

    public void completeWash() {
        currentCycle = null;
        System.out.println(machineId + " is now free.");
    }
}

public class P1_HostelLaundryQueue {
    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        WashType quick = new QuickWash();
        WashType heavy = new HeavyWash();
        WashType normal = new NormalWash();

        m1.startWash(asha, quick);
        m1.startWash(ravi, heavy);

        m2.startWash(ravi, heavy);

        if (!m1.isFree()) {
            System.out.println("M1 cycle completed.");
            m1.completeWash();
        }

        m1.startWash(neha, normal);
    }
}