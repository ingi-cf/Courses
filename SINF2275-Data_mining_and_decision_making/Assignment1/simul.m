function [play h] = simul(m,policy)
    h = [1];
    s = 1;
    play = 0;
       
    while(s ~= m.d)
        if play > 1000
            return
        end
        diceT = policy(s);
        diceR = dice(diceT);
        if(diceR == 0)
            sr = s;
        else
            p = next_paths(s,m);
            sr = p(floor(rand() * (size(p,2)))+1);
            if(diceR == 2)
                sr = next_primary(sr,m);
            end
        end

        if diceT == 2 && m.traps(sr) == 1
            %back to init case
            s = m.s0;
        elseif diceT == 2 && m.traps(sr) == 2
            %prison
            s = sr;
            play = play+ 1;
        elseif diceT == 2 && m.traps(sr) == 3
            %retreat
            s = retreat(sr,m);
        else
            s = sr;
        end
        play
        play = play+1;
        h(end+1) = s;
    end
end

function result = dice(type)
    maxval = 0;
    if(type == 1)
        maxval = 1;
    elseif(type == 2)
        maxval = 2;
    end
    result = floor(rand()*(maxval+1));
end
