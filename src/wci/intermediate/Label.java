package wci.intermediate;
import wci.intermediate.*;
/**
* <h1>Label</h1>
*
* <p>Jasmin instruction label.</p> */
  public class Label {
private static int index = 0; // index for generating label strings

private String label;
/**
* Constructor. */
private Label() {


// the label string
this.label = "L" + String.format("%03d", ++index); }
/**
* @return a new instruction label. */
public static Label newLabel() {
return new Label(); }
/**
* @return the label string. */
public String toString() {
return this.label; }
}