function [Expec, Dice] = markovDec(List)
%MARKOVDEC outputs the decisions from the markov process 
%   List represent the traps types
%   The board used is the basic board with stop on arrival

map.s0     = 1;
map.d      = 11;
map.links  = [ 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0;
                0 0 1 0 0 0 0 0 0 0 0 0 0 0 0;
                0 0 0 1 0 0 0 0 0 0 0 2 0 0 0;
                0 0 0 0 1 0 0 0 0 0 0 0 0 0 0;
                0 0 0 0 0 1 0 0 0 0 0 0 0 0 0;
                0 0 0 0 0 0 1 0 0 0 0 0 0 0 0;
                0 0 0 0 0 0 0 1 0 0 0 0 0 0 0;
                0 0 0 0 0 0 0 0 1 0 0 0 0 0 0;
                0 0 0 0 0 0 0 0 0 1 0 0 0 0 0;
                0 0 0 0 0 0 0 0 0 0 1 0 0 0 0;
                0 0 0 0 0 0 0 0 0 0 1 0 0 0 0;
                0 0 0 0 0 0 0 0 0 0 0 0 1 0 0;
                0 0 0 0 0 0 0 0 0 0 0 0 0 1 0;
                0 0 0 0 0 0 0 0 0 0 0 0 0 0 1;
                0 0 0 0 0 0 0 0 0 0 1 0 0 0 0];
            
map.traps = List;

[Dice, Expec] = snake(map);

end

