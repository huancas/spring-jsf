/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.com.controller.calculadora;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author degmi
 */
@Named(value = "calculadoraBean")
@SessionScoped
public class calculadoraBean implements Serializable {

    String panel = "";
   //funcion para quitar cero, el double  siempre deja un cero demas
    private void quitarCero(String numero) {

        for (int i = 0; i < numero.length(); i++) {
            char letra = numero.charAt(i);
            if (letra == '.') {
                String sobra = numero.substring(i);
                if (sobra.equals(".0")) {
                    panel = numero.substring(0, numero.length() - 2);
                    //display="hola";
                }

            }

        }

    }

    // bloque 2
    //actualiza la pantalla
    public void actualizar_panel(String numero) {

        panel = panel + numero;
        validar_punto_o();
        //  quitarCero(panel);
        //return panel;
    }
    //antepone un mas o menos segun su sgino
    public void mas_menos(){
        try { char init = panel.charAt(0);
        if(init=='-'){panel=panel.substring(1);  }
        else{ panel="-"+panel;                      }
            
        } catch (Exception e) {
            panel=panel+"";
        }
        
       
    }
    //funcion para dividir 1/cualquier valor  tien un try catch por si se pone un cero y sale error
    public void divi(){
      
        try { panel  =String.valueOf(1/ Double.parseDouble(panel));
            
        } catch (Exception e) {
            this.panel=panel;
        }
 
      
     // validar_punto_o();
      quitarCero(panel);
      
    }
  // valida que no se escriban puro ceros
    public void validar_punto_o() {
        // primer numero
        String inicial = String.valueOf(panel.charAt(0));
        if (inicial.equals("0")) {
            panel = panel.substring(1);
        }
        if (inicial.equals(".")) {
            panel = panel.substring(1);
        }
        //segundo numero
        String num2;
        for (int i = 0; i < panel.length(); i++) {
            char signo = panel.charAt(i);
            if (signo == '+' || signo == '-' || signo == 'x' || signo == '/' || signo == '√' || signo == '^') {
                num2 = panel.substring(i + 1);
                System.out.println(" num2 " + num2 + "  i " + i);
                if (num2.equals(".") || num2.equals("00")) {
                    panel = panel.substring(0, panel.length() - 1);
                }

            }
        }
    }
    //funcion no usada
    public void in_menos(){
        
        for (int i = 0; i < panel.length(); i++) {
              String x = "";
            double resultado = 0;
             double num1 = 0, num2 = 0;
            String num = String.valueOf(panel.charAt(i));
            x = x + num;   // leer los numeros        

            switch (num) {
                case "+":
                    num2 = Double.parseDouble(panel.substring(i));
                    num1 = Double.parseDouble(x.substring(0, i));
                    resultado = num1 + num2;
                    break;
            }
    
    }
    }
    // cuando se presiona el boton igual verifica que es lo que va hacer, sumar restar, o dvidir o otros.
    public void accion() {
        int cont=0;
        int signo =1;
        char in  = panel.charAt(0);
        if(in=='-'){  panel=panel.substring(1); signo=-1; }
        double resultado = 0;
        double num1 = 0, num2 = 0;
        String x = "";
        for (int i = 0; i < panel.length(); i++) {
            String num = String.valueOf(panel.charAt(i));
            x = x + num;   // leer los numeros        

            switch (num) {
                case "+":
                    num2 = Double.parseDouble(panel.substring(i));
                    num1 =signo* Double.parseDouble(x.substring(0, i));
                    resultado = num1 + num2;
                    break;
                case "-":
                    num2 = Double.parseDouble(panel.substring(i + 1));
                    num1 =signo* Double.parseDouble(x.substring(0, i));
                    resultado = num1 - num2;
                    break;
                case "x":
                    num2 = Double.parseDouble(panel.substring(i + 1));
                    num1 = signo*Double.parseDouble(x.substring(0, i));
                    resultado = num1 * num2;
                    break;
                case "/":
                    num2 = Double.parseDouble(panel.substring(i + 1));
                    num1 = signo*Double.parseDouble(x.substring(0, i));
                    resultado = num1 / num2;

                    break;
                case "√":
                    String inicial = String.valueOf(panel.charAt(0));
                    if (inicial.equals("√")) {
                        String base = panel.substring(1, panel.length());
                        resultado = potencia("raiz", 2, Double.parseDouble(base));
                    } else {
                        num2 = Double.parseDouble(panel.substring(i + 1));
                        num1 = Double.parseDouble(x.substring(0, i));
                        resultado = potencia("raiz", num1, num2);
                    }
                    break;
                case "^":
                    num2 = Double.parseDouble(panel.substring(i + 1));
                    num1 = signo*Double.parseDouble(x.substring(0, i));
                    resultado = potencia("potencia", num2, num1);
                    break;
                case "1/x":
                    num2 = signo*Double.parseDouble(panel.substring(i + 1));
                    resultado = 1 / num2;
                    break;
                case "%":
                    num2 = signo*Double.parseDouble(panel.substring(i + 1));
                    num1 = Double.parseDouble(x.substring(0, i));
                    resultado = (num1 /100)* num2;
                 break;

            }

        }
       
        panel = String.valueOf(resultado);
        quitarCero(panel);
    }

    public String getPanel() {
        return panel;
    }

    public void setPanel(String panel) {
        this.panel = panel;

    }

    public void clear() {
        this.panel = "";
    }
    
    // esto verifica si es una raiz o potencia y de acuerdo a eso lo calcula
    public float potencia(String a, double exp, double base) {
        float res = 0;
        if (a.equals("raiz")) {
            float raiz = (float) Math.pow(base, 1 / exp);
            res = raiz;
            System.out.println(" raiz " + raiz + " panel " + panel);
        } else if (a.equals("potencia")) {
            float potencia = (float) Math.pow(base, exp);
            res = potencia;
            System.out.println(" potencia " + potencia + " panel " + panel);
        }

        // √ ^
        return res;

    }
   // esta funcion borrra de la pantalla solo un numero cada vez que se presione
    public void borrar() {
        if (panel.length() > 0) {
            panel = panel.substring(0, panel.length() - 1);
        }
    }

}
