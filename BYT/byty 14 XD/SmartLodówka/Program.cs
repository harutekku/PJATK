using System;

namespace SmartLodówka
{
    class Program
    {
        static void Main(string[] args)
        {
            Packer packer = new Packer(new FancyDishBuilder());
            Dish kanapka = packer.createDish("Kanapka");
            System.Console.WriteLine(kanapka);
            System.Console.WriteLine();
            System.Console.WriteLine();
            packer = new Packer(new TakeawayDishBuilder());
            Dish kanapkaNaWynos = packer.createDish("Kanapka");
            System.Console.WriteLine(kanapkaNaWynos);
            System.Console.WriteLine();
            System.Console.WriteLine();
        }
    }
}
