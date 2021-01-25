using System;
using System.Collections.Generic;
using System.Text;

namespace SmartLodówka
{
    class DefrostState : State
    {
        private Program Program;

        public DefrostState(Program program)
        {
            Program = program;
            Console.WriteLine("Rozmrażanie");
            Program.light = false;
            Program.Power = 0;
            Program.ThreadBool = false;
        }

        public void close()
        {
            Program.State = new ClosedState(Program);
        }

        public void defrost()
        {
            Console.Out.WriteLine("Aktualnie rozmraża");
        }

        public Dish makeDish(Contents Contents, Packer Packer, string Name)
        {
            throw new Exception("Funkcja wyłączona, stan rozmrażania");

        }
        public void open()
        {
            Program.State = new OpenState(Program);
        }
    }
}
