function [Expec, Dice] = markovDec(List)
%MARKOVDEC outputs the decisions from the markov process 
%   List represent the traps types
%   The board used is the basic board with no loop (passing arrival=win)

map.s0     = 1; %initial square
map.d      = 11; %goal square

%affinity matrix
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
%traps types            
map.traps = List;

[Dice, Expec] = snake(map);

end

