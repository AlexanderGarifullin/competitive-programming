using System;

namespace CF1
{
    internal class Program
    {
        static void Main(string[] args)
        {
            string s = Console.ReadLine();
            string [] s1 = s.Split(' ');
            double n = double.Parse(s1[0]);
            double m = double.Parse(s1[1]);
            double a = double.Parse(s1[2]);
            long k = (long)Math.Ceiling(m / a) * (long)Math.Ceiling(n / a);
            Console.WriteLine(k);

        }
    }
}
