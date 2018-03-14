using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;



namespace Program
{
    class oceny
    {
        static void Main(string[] args)
        {
            Console.WriteLine("podaj 3 oceny i zatwierdz kazda z nich");
            Console.WriteLine("mechanika:");
            double x = double.Parse(Console.ReadLine());
            Console.WriteLine("analiza matematyczna:");
            double y = double.Parse(Console.ReadLine());
            Console.WriteLine("systemy wytwarzania i montazu:");
            double z = double.Parse(Console.ReadLine());
            student xxx  = new student(x, y, z);
            Console.WriteLine(xxx.sredniaocen());
            Console.ReadKey();
        }
    }
}
class student
{
    public double a;
    public double b;
    public double c;

    public student(double ocena, double ocena2, double ocena3)
    {
        a = ocena;
        b = ocena2;
        c = ocena3;
    }
    public double sredniaocen()
    {
        Console.WriteLine("średnia wszystkich ocen wynosi:");
        return ((a + b + c) / 3);
    }
}