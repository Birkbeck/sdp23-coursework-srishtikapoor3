package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class
// AddInstruction Class store the Label, operation code , source and result register
// It also define the execute() function to perform each instruction in machine
// It depend on the file content/instruction

/**
 * @author
 */

public class AddInstruction extends Instruction {
	private final RegisterName result;
	private final RegisterName source;

	public static String OP_CODE = "add";

	public AddInstruction(String label, RegisterName result, RegisterName source) {
		super(label, OP_CODE);
		this.result = result;
		this.source = source;
	}
	@Override
	public boolean equals (Object o)
                {
                        if (o instanceof Instruction )
                        {
			return true;
                                    } 
                                    else
                                    {
			return false;
                                    }
		}

		@Override
		public int hashCode (){

		{
			return Objects.hash(label, result , source, NORMAL_PROGRAM_COUNTER_UPDATE);

		}
		}



	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(result);
		int value2 = m.getRegisters().get(source);
		if (OP_CODE.equals("add"))
		{
		m.getRegisters().set(result, value1 + value2);
		}
		else if (OP_CODE.equals("sub"))
		{
			m.getRegisters().set(result, value1 - value2);
		}
		else if (OP_CODE.equals("mul"))
		{
			m.getRegisters().set(result, value1 * value2);
		}
		else if (OP_CODE.equals("div"))
		{
			m.getRegisters().set(result, value1 / value2);
		}
		else if (OP_CODE.equals("out"))
		{
			m.getRegisters().set(result, value2);
		}
		else if (OP_CODE.equals("mov"))
		{
			m.getRegisters().set(result, value1 );
		}

		/*else if (OP_CODE.equals("jnz"))
		{
			m.getRegisters().set(result, super.label );
		}*/

		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}



	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + result + " " + source;
	}
}
