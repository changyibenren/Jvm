package org.itstack.demo.jvm.runtime;

public class LocalVars {
    private Slot[] slots;

    public LocalVars(int maxlocals){
        if(maxlocals > 0){
            slots = new Slot[maxlocals];
            for(int i = 0; i < maxlocals; i++){
                slots[i] = new Slot();
            }
        }
    }

    //int 变量最简单，直接存取即可
    public void setInt(int id, int val){
        this.slots[id].num = val;
    }

    public int getInt(int id){
        return this.slots[id].num;
    }

    //float 变量可以先转成 int 类型，然后按 int 变量来处理
    public void setFloat(int id, float val){
        this.slots[id].num = (Float.valueOf(val)).intValue();
    }

    public Float getFloat(int id){
        return (float)this.slots[id].num;
    }

    //long 和 double 是占用 8 字节的,所以使用了局部变量表中的两个槽位分别存储前四字节和后四字节
    //long 变量则需要拆成两个 int 变量
    public void setLong(int id, long val){
        this.slots[id].num = (int)val;
        this.slots[id+1].num = (int)(val >> 32);
    }

    //两个 int 变量合并成long 变量
    public Long getLong(int id){
        int low = this.slots[id].num;
        int high = this.slots[id+1].num;
        return ((long)high << 32) | (long)low;
    }

    //double 变量可以先转成 long 类型，然后按照 long 变量来处理。
    public void setDouble(int id, double val){
        setLong(id, (long)val);
    }

    public Double getDouble(int id){
        return Double.valueOf(getLong(id));
    }

    //直接存取
    public void setRef(int id, Object ref){
        this.slots[id].ref = ref;
    }

    public Object getRef(int id){
        return this.slots[id].ref;
    }

    public Slot[] getSlots() {
        return slots;
    }

    public void setSlot(int id, Slot slot) {
        this.slots[id] = slot;
    }

    /*public String transform(){
        String string = "[";
        for(int i=0;i<this.slots.length;i++){

            string += "{"+this.slots[i].num+"},";
        }
        string+="]";
        return string;
    }*/
}
