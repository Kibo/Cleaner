package cleaner

import groovy.swing.SwingBuilder;
import java.awt.FlowLayout;
import javax.swing.*;
import java.text.Normalizer;
import java.text.Normalizer.Form;

/**
*   Clears the source text file of punctuation.
*   The source file will be overwritten!
*/
class PunctationCleaner {

    def swing
    def frame
             
    static void main(String[] args){
        def cleaner = new PunctationCleaner()
        cleaner.show()
    }

    def show(){

      swing = new SwingBuilder()

      frame = swing.frame(title:'Punctation cleaner', defaultCloseOperation:WindowConstants.EXIT_ON_CLOSE, pack:true, show:true) {

            menuBar (){              
                menuItem(text: "Help", mnemonic:'H', actionPerformed: { showHelp() })         
            }

            panel(layout:new FlowLayout()) {

                textlabel = label("Source:")
                textField(id:'pathToFile',columns:20)

                button(text:'Choose a file', actionPerformed:{
                    fc = new JFileChooser()
                    fc.fileSelectionMode = JFileChooser.FILES_ONLY
                    fc.showOpenDialog()
                    pathToFile.text=fc.selectedFile

                })

                button(text:'Clean', actionPerformed:{
                        if(pathToFile.text){
                            engine(pathToFile.text)
                            optionPane(message:'Done!').createDialog(frame, 'Message').show()
                        }
                })
            }
        }
    }

    def showHelp(event){
        JOptionPane.showMessageDialog(frame,
'''Clears the source text file of punctuation.
Attention! The source file will be overwritten!
''')
        }
        
    def engine( path ){
        def input = new File( path )
        def text = input.text
        String clear = Normalizer.normalize(text, Form.NFD).replaceAll("[^\\p{ASCII}]","");
        input.write(  clear )
    }
}

