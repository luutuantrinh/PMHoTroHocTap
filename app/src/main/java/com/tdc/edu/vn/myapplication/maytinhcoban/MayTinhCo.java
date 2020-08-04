package com.tdc.edu.vn.myapplication.maytinhcoban;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tdc.edu.vn.myapplication.R;

import java.util.Arrays;
import java.util.Stack;

class InfixToPostfix{
    boolean check_error = false;  // kiem tra ky tu dau tien la am hay duong, kiem tra loi

    public String standardizeDouble(double num){ //chuan hoa so
        int a = (int)num;
        if (a == num)
            return Integer.toString(a);
        else return Double.toString(num);
    }

    public boolean isCharPi(char c){ //kiem tra ky tu c la Pi hay khong
        if (c == 'π') return true;
        else return false;
    }

    public boolean isNumPi(double num){ //kiem tra so num la Pi hay khong
        if (num == Math.PI) return true;
        else return false;
    }

    public boolean isNum(char c){	//kiem tra ky tu c co la so khong (pi cung la so)
        if (Character.isDigit(c) || isCharPi(c)) return true;
        else return false;
    }

    public String NumToString(double num){ //chuyen so sang chuoi
        if (isNumPi(num)) return "π";
        else return standardizeDouble(num);
    }

    public double StringToNum(String s){ 	//Chuoi sang so
        if (isCharPi(s.charAt(0))) return Math.PI;
        else return Double.parseDouble(s);
    }

    public boolean isOperator(char c){ 	// kiem tra xem co phai toan tu
        char operator[] = { 'l','+', '-', '*', '/', '^', '~', 's', 'c', 't', '@', '!', '%', ')', '('}; //~ thay cho dau am (-)
        Arrays.sort(operator);
        if (Arrays.binarySearch(operator, c) > -1)
            return true;
        else return false;
    }
    public int priority(char c){		// thiet lap thu tu uu tien
        switch (c) {
            case '+' : case '-' : return 1;
            case '*' : case '/' : return 2;
            case '~' : return 3;
            case '@' : case '!' : case '^' : return 4;
            case 's' : case 'c' : case 't' : return 5;
            case 'l' : return 6;
        }
        return 0;
    }

    public boolean isOneMath(char c){ 	// kiem tra toan tu 1 ngoi
        char operator[] = { 'l','s', 'c', 't', '@', '('}; //~ thay cho dau am (-)
        Arrays.sort(operator);
        if (Arrays.binarySearch(operator, c) > -1)
            return true;
        else return false;
    }

    public String standardize(String s){ //chuan hoa bieu thuc
        String s1 = "";
        s = s.trim();
        s = s.replaceAll("\\s+"," "); //	chuan hoa s
        int open = 0, close = 0;
        for (int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if (c == '(') open++;
            if (c == ')') close++;
        }
        for (int i=0; i< (open - close); i++) // them cac dau ")" vao cuoi neu thieu
            s += ')';
        for (int i=0; i<s.length(); i++){
            if (i>0 && isOneMath(s.charAt(i)) && (s.charAt(i-1) == ')' || isNum(s.charAt(i-1)))) s1 = s1 + "*"; //	chuyen ...)(... thanh ...)*(...
            if ((i == 0 || (i>0 && !isNum(s.charAt(i-1)))) && s.charAt(i) == '-' && isNum(s.charAt(i+1))) {
                s1 = s1 + "~"; // check so am
            }
        	/*else if ((i == 0 || (i>0 && !isNum(s.charAt(i-1)))) && s.charAt(i) == '+' && isNum(s.charAt(i+1))) {
        		s1 = s1 + ""; // check dau +
        	}*/
            else if (i>0 && (isNum(s.charAt(i-1)) || s.charAt(i-1) == ')') && isCharPi(s.charAt(i))) s1 = s1 + "*" + s.charAt(i);
                // VD hoac 6π , ...)π chuyen sang 6*π , ...)*π
            else s1 = s1 + s.charAt(i);
        }
        return s1;
    }

    public String[] processString(String sMath){ // xu ly bieu thuc nhap vao thanh cac phan tu
        String s1 = "", elementMath[] = null;
        sMath = standardize(sMath);
        InfixToPostfix  ITP = new InfixToPostfix();
        for (int i=0; i<sMath.length(); i++){
            char c = sMath.charAt(i);
            if (i<sMath.length()-1 && isCharPi(c) && !ITP.isOperator(sMath.charAt(i+1))){ // error neu co dang π3
                check_error = true;
                return null;
            }
            else
            if (!ITP.isOperator(c))
                s1 = s1 + c;
            else s1 = s1 + " " + c + " ";
        }
        s1 = s1.trim();
        s1 = s1.replaceAll("\\s+"," "); //	chuan hoa s1
        elementMath = s1.split(" "); //tach s1 thanh cac phan tu
        return elementMath;
    }

    public String[] postfix(String[] elementMath){
        InfixToPostfix  ITP = new InfixToPostfix();
        String s1 = "", E[];
        Stack<String> S = new Stack<String>();
        for (int i=0; i<elementMath.length; i++){ 	// duyet cac phan tu
            char c = elementMath[i].charAt(0);		// c la ky tu dau tien cua moi phan tu

            if (!ITP.isOperator(c)) 				// neu c khong la toan tu
                s1 = s1 + elementMath[i] + " ";		// xuat elem vao s1
            else{									// c la toan tu
                if (c == '(') S.push(elementMath[i]);	// c la "(" -> day phan tu vao Stack
                else{
                    if (c == ')'){						// c la ")"
                        char c1;						//duyet lai cac phan tu trong Stack
                        do{
                            c1 = S.peek().charAt(0);	// c1 la ky tu dau tien cua phan tu
                            if (c1 != '(') s1 = s1 + S.peek() + " "; 	// trong khi c1 != "("
                            S.pop();
                        }while (c1 != '(');
                    }
                    else{
                        // Stack khong rong va trong khi phan tu trong Stack co do uu tien >= phan tu hien tai
                        while (!S.isEmpty() && ITP.priority(S.peek().charAt(0)) >= ITP.priority(c))
                            s1 = s1 + S.pop() + " ";
                        S.push(elementMath[i]); // 	dua phan tu hien tai vao Stack
                    }
                }
            }
        }
        while (!S.isEmpty()) s1 = s1 + S.pop() + " "; // Neu Stack con phan tu thi day het vao s1
        E = s1.split(" ");	//	tach s1 thanh cac phan tu
        return E;
    }

    public String valueMath(String[] elementMath){
        Stack <Double> S = new Stack<Double>();
        InfixToPostfix  ITP = new InfixToPostfix();
        double num = 0.0;
        for (int i=0; i<elementMath.length; i++){
            char c = elementMath[i].charAt(0);
            if (isCharPi(c)) S.push(Math.PI);	// neu la pi
            else{
                if (!ITP.isOperator(c)) S.push(Double.parseDouble(elementMath[i])); //so
                else{	// toan tu

                    double num1 = S.pop();
                    switch (c) {
                        case '~' : num = -num1; break;
                        case 's' : num = Math.sin(num1); break;
                        case 'c' : num = Math.cos(num1); break;
                        case 't' : num = Math.tan(num1); break;
                        case 'l' : num = Math.log10(num1); break;
                        case '%' : num = num1/100; break;
                        case '@' : {
                            if (num1 >=0){
                                num = Math.sqrt(num1); break;
                            }
                            else check_error = true;
                        }
                        case '!' : {
                            if (num1 >= 0 && (int)num1 == num1){
                                num = 1;
                                for (int j=1; j<=(int)num1; j++)
                                    num = num * j;
                            }
                            else check_error = true;
                        }
                        default : break;
                    }
                    if (!S.empty()){
                        double num2 = S.peek();
                        switch (c) {
                            //-----------------------
                            case '+' : num = num2 + num1; S.pop(); break;
                            case '-' : num = num2 - num1; S.pop(); break;
                            case '*' : num = num2 * num1; S.pop(); break;
                            case '/' : {
                                if (num1 != 0) num = num2 / num1;
                                else check_error = true;
                                S.pop(); break;
                            }
                            case '^' : num = Math.pow(num2, num1); S.pop(); break;
                        }
                    }
                    S.push(num);
                }
            }
        }
        return NumToString(S.pop());
    }
}

public class MayTinhCo extends AppCompatActivity implements View.OnClickListener {
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0;
    Button btnxxx,btnxcanx,btnlog10,btnmc,btnxoa1,btnxoahet,btnxoa2,chamthang;
    Button btnngoac1,btnngoac2,btnmcong,btnmtru,btnmro;
    Button btnnhan,btnchia,btncong,btntru,btnsin,btncos,btnans,btnbang,btncham,btntram,btntan,btnbi;
    EditText edtnumberc,edtnumber;

    String textMath = "", textAns = "0", screenTextMath = "";
    double num1 = 0, num2 = 0, ans = 0;
    char dau = ' ';
    int checkSubmit = 0;
    Double var1;
    Double var2;

    // mac dinh cac  bieu thuc nay bang false khi nhan vao cac nut thi thanh tru
    Boolean addition = false, subtract = false, multiply = false, divide = false;


    /*Add : cong, Minus :tru, Multiply: nhan, Dived : chia, Percent : phan tram, Sqrt: can, inverse : nghich dao,
     Submit : gui, Facetorial giai thua*/
    public void error(){
        edtnumber.setText("Math Error !");
        textAns = textMath = screenTextMath = "";
    }
    public void submit(String[] elementMath) {
        InfixToPostfix ITP = new InfixToPostfix();
        if (textMath.length() > 0) {
            try {
                if (!ITP.check_error)
                    elementMath = ITP.processString(textMath);        //	tach bieu thuc thanh cac phan tu
                if (!ITP.check_error)
                    elementMath = ITP.postfix(elementMath);        // 	dua cac phan tu ve dang postfix
                if (!ITP.check_error) textAns = ITP.valueMath(elementMath);        //lay gia tri
                edtnumber.setText(textAns);
                textMath = textAns;
                screenTextMath = textAns;
                checkSubmit = 1;
            } catch (Exception e) {
                error();
            }
            if (ITP.check_error) error();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_may_tinh_co);
        edtnumberc = findViewById(R.id.edtnumberc);
        edtnumber = findViewById(R.id.edtnumber);
        btnmcong = findViewById(R.id.btnmcong);
        btnmtru = findViewById(R.id.btnmtru);
        btnmro = findViewById(R.id.btnmro);
        //
        btnlog10 = (Button) findViewById(R.id.btnlog10);
        btnlog10.setOnClickListener((View.OnClickListener) this);

        btnmc = (Button) findViewById(R.id.btnmc);
        btnmc.setOnClickListener((View.OnClickListener) this);
        //
        chamthang = (Button) findViewById(R.id.chamthang);
        chamthang.setOnClickListener((View.OnClickListener) this);


        btnmcong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtnumber.setText(edtnumberc.getText()+"->MR+");
            }
        });

        btnmtru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtnumber.setText(edtnumber.getText()+"->MR-");
            }
        });
        btnmro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtnumberc.setText(edtnumberc.getText()+"-65");
            }
        });

        btn0 = (Button) findViewById(R.id.btn0);
        btn0.setOnClickListener((View.OnClickListener) this);

        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener((View.OnClickListener) this);

        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener((View.OnClickListener) this);

        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener((View.OnClickListener) this);

        btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener((View.OnClickListener) this);

        btn5 = (Button) findViewById(R.id.btn5);
        btn5.setOnClickListener((View.OnClickListener) this);

        btn6 = (Button) findViewById(R.id.btn6);
        btn6.setOnClickListener((View.OnClickListener) this);

        btn7 = (Button) findViewById(R.id.btn7);
        btn7.setOnClickListener((View.OnClickListener) this);

        btn8 = (Button) findViewById(R.id.btn8);
        btn8.setOnClickListener((View.OnClickListener) this);

        btn9 = (Button) findViewById(R.id.btn9);
        btn9.setOnClickListener((View.OnClickListener) this);

        btnbi = (Button) findViewById(R.id.btnbi);
        btnbi.setOnClickListener((View.OnClickListener) this);

        btncong = (Button) findViewById(R.id.btncong);
        btncong.setOnClickListener((View.OnClickListener) this);

        btntru = (Button) findViewById(R.id.btntru);
        btntru.setOnClickListener((View.OnClickListener) this);

        btnbang = (Button) findViewById(R.id.btnbang);
        btnbang.setOnClickListener((View.OnClickListener) this);

        btnnhan = (Button) findViewById(R.id.btnnhan);
        btnnhan.setOnClickListener((View.OnClickListener) this);

        btnchia = (Button) findViewById(R.id.btnchia);
        btnchia.setOnClickListener((View.OnClickListener) this);

        btntram = (Button) findViewById(R.id.btntram);
        btntram.setOnClickListener((View.OnClickListener) this);

        btnxcanx = (Button) findViewById(R.id.btnxcanx);
        btnxcanx.setOnClickListener((View.OnClickListener) this);

        btnxxx = (Button) findViewById(R.id.btnx1);
        btnxxx.setOnClickListener((View.OnClickListener) this);
        //ngic dao
        btnans = (Button) findViewById(R.id.btnans);
        btnans.setOnClickListener((View.OnClickListener) this);

        btnngoac1 = (Button) findViewById(R.id.btnngoac1);
        btnngoac1.setOnClickListener((View.OnClickListener) this);

        btnngoac2 = (Button) findViewById(R.id.btnngoac2);
        btnngoac2.setOnClickListener((View.OnClickListener) this);

        btnsin = (Button) findViewById(R.id.btnsin);
        btnsin.setOnClickListener((View.OnClickListener) this);

        btncos = (Button) findViewById(R.id.btncos);
        btncos.setOnClickListener((View.OnClickListener) this);

        btntan = (Button) findViewById(R.id.btntan);
        btntan.setOnClickListener((View.OnClickListener) this);

        btncham = (Button) findViewById(R.id.btncham);
        btncham.setOnClickListener((View.OnClickListener) this);

        btnxoa1 = (Button) findViewById(R.id.btnxoa1);
        btnxoa1.setOnClickListener((View.OnClickListener) this);

        btnxoahet = (Button) findViewById(R.id.btnxoahet);
        btnxoahet.setOnClickListener((View.OnClickListener) this);

        btnxoa1 = (Button) findViewById(R.id.btnxoa1);
        btnxoa1.setOnClickListener((View.OnClickListener) this);
        btnxoa2 = (Button) findViewById(R.id.btnxoa2);
        btnxoa2.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String elementMath[] = null;

        if (id == R.id.btnlog10){ // log10
            if (screenTextMath.length()<48) {
                if (checkSubmit == 1) { screenTextMath = textMath = ""; checkSubmit = 0; }
                textMath += "l(";
                screenTextMath +="Log10(";
            }
            edtnumberc.setText(screenTextMath);
        }
        if (id == R.id.chamthang){ //luy thua
            if (screenTextMath.length()<48) {
                if (checkSubmit == 1) { screenTextMath = textMath = ""; checkSubmit = 0; }
                textMath += "!";
                screenTextMath +="!";
            }
            edtnumberc.setText(screenTextMath);
        }
        if (id == R.id.btn0){
            if (screenTextMath.length()<48) {	//neu bieu thuc nhap vao <48 ky tu
                if (checkSubmit == 1) { screenTextMath = textMath = ""; checkSubmit = 0; }
                textMath += "0";
                screenTextMath += "0";
            }
            edtnumberc.setText(screenTextMath);
        }
        if (id == R.id.btn1){
            if (screenTextMath.length()<48) {
                if (checkSubmit == 1) { screenTextMath = textMath = ""; checkSubmit = 0; }
                textMath += "1";
                screenTextMath += "1";
            }
            edtnumberc.setText(screenTextMath);
        }
        if (id == R.id.btn2){
            if (screenTextMath.length()<48) {
                if (checkSubmit == 1) { screenTextMath = textMath = ""; checkSubmit = 0; }
                textMath += "2";
                screenTextMath += "2";
            }
            edtnumberc.setText(screenTextMath);
        }
        if (id == R.id.btn3){
            if (screenTextMath.length()<48) {
                if (checkSubmit == 1) { screenTextMath = textMath = ""; checkSubmit = 0; }
                textMath += "3";
                screenTextMath += "3";
            }
            edtnumberc.setText(screenTextMath);
        }
        if (id == R.id.btn4){
            if (screenTextMath.length()<48) {
                if (checkSubmit == 1) { screenTextMath = textMath = ""; checkSubmit = 0; }
                textMath += "4";
                screenTextMath += "4";
            }
            edtnumberc.setText(screenTextMath);
        }
        if (id == R.id.btn5){
            if (screenTextMath.length()<48) {
                if (checkSubmit == 1) { screenTextMath = textMath = ""; checkSubmit = 0; }
                textMath += "5";
                screenTextMath += "5";
            }
            edtnumberc.setText(screenTextMath);
        }
        if (id == R.id.btn6){
            if (screenTextMath.length()<48) {
                if (checkSubmit == 1) { screenTextMath = textMath = ""; checkSubmit = 0; }
                textMath += "6";
                screenTextMath += "6";
            }
            edtnumberc.setText(screenTextMath);
        }
        if (id == R.id.btn7){
            if (screenTextMath.length()<48) {
                if (checkSubmit == 1) { screenTextMath = textMath = ""; checkSubmit = 0; }
                textMath += "7";
                screenTextMath += "7";
            }
            edtnumberc.setText(screenTextMath);
        }
        if (id == R.id.btn8){
            if (screenTextMath.length()<48) {
                if (checkSubmit == 1) { screenTextMath = textMath = ""; checkSubmit = 0; }
                textMath += "8";
                screenTextMath += "8";
            }
            edtnumberc.setText(screenTextMath);
        }
        if (id == R.id.btn9){
            if (screenTextMath.length()<48) {
                if (checkSubmit == 1) { screenTextMath = textMath = ""; checkSubmit = 0; }
                textMath += "9";
                screenTextMath += "9";
            }
            edtnumberc.setText(screenTextMath);
        }
        if (id == R.id.btncham){
            if (screenTextMath.length()<48) {
                if (checkSubmit == 1) { screenTextMath = textMath = ""; checkSubmit = 0; }
                if (textMath.equals("")) textMath += "0";
                textMath += ".";
                screenTextMath += ".";
            }
            edtnumberc.setText(screenTextMath);
        }
        if (id == R.id.btnbi){
            if (screenTextMath.length()<48) {
                if (checkSubmit == 1) { screenTextMath = textMath = ""; checkSubmit = 0; }
                textMath += "π";
                screenTextMath += "π";
            }
            edtnumberc.setText(screenTextMath);
        }
        if (id == R.id.btncong){
            if (screenTextMath.length()<48) {
                if (checkSubmit == 1) { checkSubmit = 0; }
                textMath += "+";
                screenTextMath += "+";
            }
            edtnumberc.setText(screenTextMath);
        }
        if (id == R.id.btntru){
            if (screenTextMath.length()<48) {
                if (checkSubmit == 1) { checkSubmit = 0; }
                textMath += "-";
                screenTextMath += "-";
            }
            edtnumberc.setText(screenTextMath);
        }
        if (id == R.id.btnnhan){
            if (screenTextMath.length()<48 && screenTextMath.length() > 0) {
                if (checkSubmit == 1) { checkSubmit = 0; }
                textMath += "*";
                screenTextMath += "*";
            }
            edtnumberc.setText(screenTextMath);
        }
        if (id == R.id.btnchia){
            if (screenTextMath.length()<48 && screenTextMath.length() > 0) {
                if (checkSubmit == 1) { checkSubmit = 0; }
                textMath += "/";
                screenTextMath += "/";
            }
            edtnumberc.setText(screenTextMath);
        }

        if (id == R.id.btnx1){ //luy thua
            if (screenTextMath.length()<48 && screenTextMath.length() > 0) {
                if (checkSubmit == 1) { checkSubmit = 0; }
                textMath += "^(";
                screenTextMath += "^(";
            }
            edtnumberc.setText(screenTextMath);
        }

        if (id == R.id.btnxcanx){ // can bac 2
            if (screenTextMath.length()<48) {
                if (checkSubmit == 1) { screenTextMath = textMath = ""; checkSubmit = 0; }
                textMath += "@";
                screenTextMath +="√";
            }
            edtnumberc.setText(screenTextMath);
        }

        if (id == R.id.btnsin){ // sin
            if (screenTextMath.length()<48) {
                if (checkSubmit == 1) { screenTextMath = textMath = ""; checkSubmit = 0; }
                textMath += "s(";
                screenTextMath +="Sin(";
            }
            edtnumberc.setText(screenTextMath);
        }

        if (id == R.id.btncos){ // cos
            if (screenTextMath.length()<48) {
                if (checkSubmit == 1) { screenTextMath = textMath = ""; checkSubmit = 0; }
                textMath += "c(";
                screenTextMath +="Cos(";
            }            edtnumberc.setText(screenTextMath);

        }

        if (id == R.id.btntan){ //tan
            if (screenTextMath.length()<48) {
                if (checkSubmit == 1) { screenTextMath = textMath = ""; checkSubmit = 0; }
                textMath += "t(";
                screenTextMath +="Tan(";
            }
            edtnumberc.setText(screenTextMath);
        }

        //luy thua

        if (id == R.id.btnngoac1){
            if (textMath.length()<48) {
                if (checkSubmit == 1) { checkSubmit = 0; }
                textMath += "(";
                screenTextMath +="(";
            }
            edtnumberc.setText(screenTextMath);
        }

        if (id == R.id.btnngoac2){
            if (textMath.length()<48 && textMath.length() > 0) {
                textMath += ")";
                screenTextMath +=")";
            }
            edtnumberc.setText(screenTextMath);
        }
        if (id == R.id.percent){ 	// phan tram
            if (screenTextMath.length() == 0) screenTextMath = "0";
            screenTextMath = "(" + screenTextMath + ")%";
            edtnumberc.setText(screenTextMath);
            if (checkSubmit == 0) submit(elementMath);
            textMath = textAns + "/100";
            submit(elementMath);
        }
        if (id == R.id.btnans){ 	// nghich dao
            if (screenTextMath.length() == 0) screenTextMath = "0";
            screenTextMath = "1/(" + screenTextMath + ")";
            edtnumberc.setText(screenTextMath);
            if (checkSubmit == 0) submit(elementMath);
            textMath = "1/" + textAns;
            submit(elementMath);
        }
        if (id == R.id.btnbang){
            submit(elementMath);
        }
        if (id == R.id.btnxoahet){
            textMath = "";
            screenTextMath = "";
            textAns = "0";
            edtnumber.setText(textAns);
            edtnumberc.setText("|");
        }
        if (id == R.id.btnxoa1){
            if (edtnumberc.length()>0){
                char c = textMath.charAt(textMath.length()-1);
                if (textMath.length() > 1 && c == '(' && textMath.charAt(textMath.length()-2) == '^'){
                    screenTextMath = screenTextMath.substring(0,screenTextMath.length()-2);
                    textMath = textMath.substring(0,textMath.length()-2);
                }
                else if (textMath.length() > 1 && c == '(' && (textMath.charAt(textMath.length()-2) == 's' || textMath.charAt(textMath.length()-2) == 'c' || textMath.charAt(textMath.length()-2) == 't') ){
                    textMath = textMath.substring(0,textMath.length()-2);
                    screenTextMath = screenTextMath.substring(0,screenTextMath.length()-4);
                }
                else if(textMath.length() > 1 && (textMath.charAt(textMath.length()-2) == 'l'))
                {
                    textMath = textMath.substring(0,textMath.length()-2);
                    screenTextMath = screenTextMath.substring(0,screenTextMath.length()-6);
                }
                else {
                    textMath = textMath.substring(0,textMath.length()-1);
                    screenTextMath = screenTextMath.substring(0,screenTextMath.length()-1);
                }
            }
            edtnumberc.setText(screenTextMath);
        }
        if (id == R.id.btnxoa2){
            if (edtnumberc.length()>0){
                char c = textMath.charAt(textMath.length()-1);
                if (textMath.length() > 1 && c == '(' && textMath.charAt(textMath.length()-2) == '^'){
                    screenTextMath = screenTextMath.substring(0,screenTextMath.length()-2);
                    textMath = textMath.substring(0,textMath.length()-2);
                }
                else if (textMath.length() > 1 && c == '(' && (textMath.charAt(textMath.length()-2) == 's' || textMath.charAt(textMath.length()-2) == 'c' || textMath.charAt(textMath.length()-2) == 't') ){
                    textMath = textMath.substring(0,textMath.length()-2);
                    screenTextMath = screenTextMath.substring(0,screenTextMath.length()-4);
                }
                else if(textMath.length() > 1 && (textMath.charAt(textMath.length()-2) == 'l'))
                {
                    textMath = textMath.substring(0,textMath.length()-2);
                    screenTextMath = screenTextMath.substring(0,screenTextMath.length()-6);
                }
                else {
                    textMath = textMath.substring(0,textMath.length()-1);
                    screenTextMath = screenTextMath.substring(0,screenTextMath.length()-1);
                }
            }
            edtnumberc.setText(screenTextMath);
        }


    }
    public void colorChange(Button b){
        b.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }
    public void colorChangec(Button c){
        c.setBackgroundColor(getResources().getColor(R.color.gray));
    }
}