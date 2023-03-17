package sml;
import sml.instruction.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import static sml.Registers.Register;

/**
 * This class ....
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author ...
 */
public final class Translator {

    private final String fileName; // source file of SML code
    
    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    public Translator(String fileName) {
        this.fileName =  fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();
                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                    {  boolean found = false;
                      
                       for(Instruction p: program)  // Iterate the program list for check the label exist or not
                       {
                           if(label.equals(p.getLabel()))  // compare the existing and new label
                           {
                               found = true;
                           }
                       }
                       if(found == false)
                       {
                           labels.addLabel(label, program.size());
                           
                       }
                       else
                       {
                           System.out.println(label+"Exist in Instruction List");
                       }
                    }
                    program.add(instruction);
                }
            }
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) {
        if (line.isEmpty())
            return null;

        String opcode = scan();
       
        AddInstruction.OP_CODE = switch(opcode) 
        {   case "add","sub","mul","div","mov","out","jnz" -> opcode; 
             default -> "";
         };
        if(AddInstruction.OP_CODE.equals(""))
            return null;
        else
        {
                String r = scan();
                String s = scan();               
                return new AddInstruction(label, Register.valueOf(r), Register.valueOf(s));
        }
            /*
                switch (opcode) {
            case "add" -> {   // TODO:  compare the operation code is "add, sub,mul,div,mov,out,jnz"
                AddInstruction.OP_CODE ="add";
                String r = scan();
                String s = scan();               
                return new AddInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }
            case "sub" -> {
                AddInstruction.OP_CODE ="sub";
                String r = scan();
                String s = scan();
                return new AddInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }
            case "mul" -> {
                AddInstruction.OP_CODE ="mul";
                String r = scan();
                String s = scan();
                return new AddInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }
            case "div" -> {
                AddInstruction.OP_CODE ="div";
                String r = scan();
                String s = scan();
                return new AddInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }
            case "out" -> {
                AddInstruction.OP_CODE ="out";
                String r = scan();
                String s = scan();
                return new AddInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }
            case "mov" -> {
                AddInstruction.OP_CODE ="mov";
                String r = scan();
                String s = scan();
                return new AddInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }
            case "jnz" -> {
                AddInstruction.OP_CODE ="jnz";
                String r = scan();
                String s = scan();
                return new AddInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }

            

            // TODO: Then, replace the switch by using the Reflection API

            // TODO: Next, use dependency injection to allow this machine class
            //       to work with different sets of opcodes (different CPUs)

            default -> {
                System.out.println("Unknown instruction: " + opcode);
            }
        }
        return null;*/
    }


    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }
}
