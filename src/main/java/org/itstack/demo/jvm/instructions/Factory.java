package org.itstack.demo.jvm.instructions;


import org.itstack.demo.jvm.instructions.base.Instruction;
import org.itstack.demo.jvm.instructions.comparsions.if_icmp.*;
import org.itstack.demo.jvm.instructions.comparsions.ifcond.*;
import org.itstack.demo.jvm.instructions.control.GOTO;
import org.itstack.demo.jvm.instructions.control.RETURN;
import org.itstack.demo.jvm.instructions.dup.DUPTWO;
import org.itstack.demo.jvm.instructions.dup.DUP;
import org.itstack.demo.jvm.instructions.load.aload.*;
import org.itstack.demo.jvm.instructions.load.dload.*;
import org.itstack.demo.jvm.instructions.load.fload.*;
import org.itstack.demo.jvm.instructions.load.iload.*;
import org.itstack.demo.jvm.instructions.load.lload.*;
import org.itstack.demo.jvm.instructions.math.add.*;
import org.itstack.demo.jvm.instructions.math.and.*;
import org.itstack.demo.jvm.instructions.math.div.*;
import org.itstack.demo.jvm.instructions.math.iinc.IINC;
import org.itstack.demo.jvm.instructions.math.mul.*;
import org.itstack.demo.jvm.instructions.math.neg.*;
import org.itstack.demo.jvm.instructions.math.or.*;
import org.itstack.demo.jvm.instructions.math.rem.*;
import org.itstack.demo.jvm.instructions.math.sub.*;
import org.itstack.demo.jvm.instructions.nop.NOP;
import org.itstack.demo.jvm.instructions.pop.*;
import org.itstack.demo.jvm.instructions.references.GET_STATIC;
import org.itstack.demo.jvm.instructions.references.INVOKE_VIRTUAL;
import org.itstack.demo.jvm.instructions.stores.astore.*;
import org.itstack.demo.jvm.instructions.stores.dstore.*;
import org.itstack.demo.jvm.instructions.stores.fstore.*;
import org.itstack.demo.jvm.instructions.stores.istore.*;
import org.itstack.demo.jvm.instructions.stores.lstore.*;
import org.itstack.demo.jvm.instructions.swap.SWAP;
import org.itstack.demo.jvm.instructions.push.*;

public class Factory {

    public static Instruction newInstruction(byte opcode) {
        switch (opcode) {
            case 0x00:
                return new NOP();
            case 0x01:
                return new PUSHNULL();
            case 0x02:
                return new IPUSHM1();
            case 0x03:
                return new IPUSH0();
            case 0x04:
                return new IPUSH1();
            case 0x05:
                return new IPUSH2();
            case 0x06:
                return new IPUSH3();
            case 0x07:
                return new IPUSH4();
            case 0x08:
                return new IPUSH5();
            case 0x09:
                return new LPUSH0();
            case 0x0a:
                return new LPUSH1();
            case 0x0b:
                return new FPUSH0();
            case 0x0c:
                return new FPUSH1();
            case 0x0d:
                return new FPUSH2();
            case 0x0e:
                return new DPUSH0();
            case 0x0f:
                return new DPUSH1();
            case 0x10:
                return new BIPUSH();
            case 0x11:
                return new SIPUSH();
            case 0x15:
                return new ILOAD();
            case 0x16:
                return new LLOAD();
            case 0x17:
                return new FLOAD();
            case 0x18:
                return new DLOAD();
            case 0x19:
                return new ALOAD();
            case 0x1a:
                return new ILOAD0();
            case 0x1b:
                return new ILOAD1();
            case 0x1c:
                return new ILOAD2();
            case 0x1d:
                return new ILOAD3();
            case 0x1e:
                return new LLOAD0();
            case 0x1f:
                return new LLOAD1();
            case 0x20:
                return new LLOAD2();
            case 0x21:
                return new LLOAD3();
            case 0x22:
                return new FLOAD0();
            case 0x23:
                return new FLOAD1();
            case 0x24:
                return new FLOAD2();
            case 0x25:
                return new FLOAD3();
            case 0x26:
                return new DLOAD0();
            case 0x27:
                return new DLOAD1();
            case 0x28:
                return new DLOAD2();
            case 0x29:
                return new DLOAD3();
            case 0x2a:
                return new ALOAD0();
            case 0x2b:
                return new ALOAD1();
            case 0x2c:
                return new ALOAD2();
            case 0x2d:
                return new ALOAD3();
            case 0x36:
                return new ISTORE();
            case 0x37:
                return new LSTORE();
            case 0x38:
                return new FSTORE();
            case 0x39:
                return new DSTORE();
            case 0x3a:
                return new ASTORE();
            case 0x3b:
                return new ISTORE_0();
            case 0x3c:
                return new ISTORE_1();
            case 0x3d:
                return new ISTORE_2();
            case 0x3e:
                return new ISTORE_3();
            case 0x3f:
                return new LSTORE_0();
            case 0x40:
                return new LSTORE_1();
            case 0x41:
                return new LSTORE_2();
            case 0x42:
                return new LSTORE_3();
            case 0x43:
                return new FSTORE_0();
            case 0x44:
                return new FSTORE_1();
            case 0x45:
                return new FSTORE_2();
            case 0x46:
                return new FSTORE_3();
            case 0x47:
                return new DSTORE_0();
            case 0x48:
                return new DSTORE_1();
            case 0x49:
                return new DSTORE_2();
            case 0x4a:
                return new DSTORE_3();
            case 0x4b:
                return new ASTORE_0();
            case 0x4c:
                return new ASTORE_1();
            case 0x4d:
                return new ASTORE_2();
            case 0x4e:
                return new ASTORE_3();
            case 0x57:
                return new POP();
            case 0x58:
                return new POP2();
            case 0x59:
                return new DUP();
            /*case 0x5a:
                return new DUP_X1();
            case 0x5b:
                return new DUP_X2();*/
            case 0x5c:
                return new DUPTWO();
            /*case 0x5d:
                return new DUP2_X1();
            case 0x5e:
                return new DUP2_X2();*/
            case 0x5f:
                return new SWAP();
            case 0x60:
                return new IADD();
            case 0x61:
                return new LADD();
            case 0x62:
                return new FADD();
            case 0x63:
                return new DADD();
            case 0x64:
                return new ISUB();
            case 0x65:
                return new LSUB();
            case 0x66:
                return new FSUB();
            case 0x67:
                return new DSUB();
            case 0x68:
                return new IMUL();
            case 0x69:
                return new LMUL();
            case 0x6a:
                return new FMUL();
            case 0x6b:
                return new DMUL();
            case 0x6c:
                return new IDIV();
            case 0x6d:
                return new LDIV();
            case 0x6e:
                return new FDIV();
            case 0x6f:
                return new DDIV();
            case 0x70:
                return new IREM();
            case 0x71:
                return new LREM();
            case 0x72:
                return new FREM();
            case 0x73:
                return new DREM();
            case 0x74:
                return new INEG();
            case 0x75:
                return new LNEG();
            case 0x76:
                return new FNEG();
            case 0x77:
                return new DNEG();
            /*case 0x78:
                return new ISHL();
            case 0x79:
                return new LSHL();
            case 0x7a:
                return new ISHR();
            case 0x7b:
                return new LSHR();
            case 0x7c:
                return new IUSHR();
            case 0x7d:
                return new LUSHR();*/
            case 0x7e:
                return new IAND();
            case 0x7f:
                return new LAND();
            case (byte) 0x80:
                return new IOR();
            case (byte) 0x81:
                return new LOR();
            /*case (byte) 0x82:
                return new IXOR();
            case (byte) 0x83:
                return new LXOR();*/
            case (byte) 0x84:
                return new IINC();
            /*case (byte) 0x85:
                return new I2L();
            case (byte) 0x86:
                return new I2F();
            case (byte) 0x87:
                return new I2D();
            case (byte) 0x88:
                return new L2I();
            case (byte) 0x89:
                return new L2F();
            case (byte) 0x8a:
                return new L2D();
            case (byte) 0x8b:
                return new F2I();
            case (byte) 0x8c:
                return new F2L();
            case (byte) 0x8d:
                return new F2D();
            case (byte) 0x8e:
                return new D2I();
            case (byte) 0x8f:
                return new D2L();
            case (byte) 0x90:
                return new D2F();
            case (byte) 0x91:
                return new I2B();
            case (byte) 0x92:
                return new I2C();
            case (byte) 0x93:
                return new I2S();
            case (byte) 0x94:
                return new LCMP();
            case (byte) 0x95:
                return new FCMPL();
            case (byte) 0x96:
                return new FCMPG();
            case (byte) 0x97:
                return new DCMPL();
            case (byte) 0x98:
                return new DCMPG();*/
            case (byte) 0x99:
                return new IFEQ();
            case (byte) 0x9a:
                return new IFNE();
            case (byte) 0x9b:
                return new IFLT();
            case (byte) 0x9c:
                return new IFGE();
            case (byte) 0x9d:
                return new IFGT();
            case (byte) 0x9e:
                return new IFLE();
            case (byte) 0x9f:
                return new IF_ICMPEQ();
            case (byte) 0xa0:
                return new IF_ICMPNE();
            case (byte) 0xa1:
                return new IF_ICMPLT();
            case (byte) 0xa2:
                return new IF_ICMPGE();
            case (byte) 0xa3:
                return new IF_ICMPGT();
            case (byte) 0xa4:
                return new IF_ICMPLE();
            /*case (byte) 0xa5:
                return new IF_ACMPEQ();
            case (byte) 0xa6:
                return new IF_ACMPNE();*/
            case (byte) 0xa7:
                return new GOTO();
            case (byte) 0xb1:
                return new RETURN();
            case (byte) 0xb2:
                return new GET_STATIC();
            case (byte) 0xb6:
                return new INVOKE_VIRTUAL();
            /*case (byte) 0xaa:
                return new TABLE_SWITCH();
            case (byte) 0xab:
                return new LOOKUP_SWITCH();
            case (byte) 0xc4:
                return new WIDE();
            case (byte) 0xc6:
                return new IFNULL();
            case (byte) 0xc7:
                return new IFNONNULL();
            case (byte) 0xc8:
                return new GOTO_W();*/
            default:
                return null;

        }

    }

}
