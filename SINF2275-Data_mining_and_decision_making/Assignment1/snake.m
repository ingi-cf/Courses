function [p,v] = snake(traps)
    map = [ 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0;
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
    
    mapSize = size(map,2)
    goal    = 11
    rproba = riskyDiceProb(map,traps);
    sproba = securityDiceProb(map);

    v = ones(1,mapSize);
    p = ones(1,mapSize);
    c = ones(1,mapSize);
    v(goal) = 0;
    for i=1:100
        %todo add a smart thing here
        for i = 1:mapSize
            if i != goal
                v(i) = min([(c(i) + sproba(i,:) * v'), (c(i) + rproba(i,:) * v')]);
            end
        end
        for i = 1:mapSize
            [ans p(i)] = min([(sproba(i,:) * (v + ones(1,mapSize)*c(i))') (c(i) + rproba(i,:) * v')]);
        end
    end
       
endfunction
function probas = securityDiceProb(map)
    probas = zeros(size(map));
    for i = 1:size(map)(1)
        probas(i,i) = 0.5;
        np = next_paths(i,map);
        for j = np
            probas(i,j)+= 0.5/size(np,2);
        end
    end
endfunction

function probas = riskyDiceProb(map,traps)
    probas = zeros(size(map));
    for i = 1:size(map,1)
        %if the dice is 0
        if traps(i) == 1
            probas(i,1) = 1/3;
        else
            probas(i,i) = 1/3;
        end

        %if the dice is not 0
        np = next_paths(i,map);
        for j = np
            %if the dice is 1
            if traps(j) == 1
                probas(i,1)+= (1/3)/size(np,2);
            else
                probas(i,j)+= (1/3)/size(np,2);
            end
            
            %if the dice is 2
            %we go in the primary state after j
            k = next_primary(j,map);
            %if the dice is 1
            if traps(k) == 1
                probas(i,1)+= (1/3)/size(np,2);
            else
                probas(i,k)+= (1/3)/size(np,2);
            end

        end
    end
endfunction


