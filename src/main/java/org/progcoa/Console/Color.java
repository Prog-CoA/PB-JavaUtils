package org.progcoa.Console;

@SuppressWarnings("all")
public enum Color {

    RED{
        @Override
        public String toString(){
            return "\u001b[00;31m";
        }
    },

    GREEN{
        @Override
        public String toString(){
            return "\u001b[00;32m";
        }
    },

    YELLOW{
        @Override
        public String toString(){
            return "\u001b[00;33m";
        }
    },

    PURPLE{
        @Override
        public String toString(){
            return  "\u001b[00;34m";
        }
    },

    PINK{
        @Override
        public String toString(){
            return "\u001b[00;35m";
        }
    },

    CYAN{
        @Override
        public String toString(){
            return "\u001b[00;36m";
        }
    }
}
