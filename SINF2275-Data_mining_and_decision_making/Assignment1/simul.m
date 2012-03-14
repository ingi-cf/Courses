function play,h = simul(map,traps,policy)
    h = []
    s = 1
    play = 0
    while(s != 11)
        diceT = policy(s);
        diceR = dice(diceT);
        if(diceR == 0)
            sr = s;
        else
            p = next_paths(s,map);
            sr = p(floor(rand() * (size(p,2)))+1);
            if(diceR == 2)
                sr = next_primary(sr,map);
            end
        end
        if(diceT == 2 && traps(sr) == 1)
            s = 1;
        else
            s = sr;
        end
        play +=1
        h(end+1) = s
    end
endfunction

function result = dice(type)
    maxval = 0
    if(type == 1)
        maxval = 1;
    elseif(type == 2)
        maxval = 2;
    end
    result = floor(rand()*(maxval+1))

