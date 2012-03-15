function stats = simulate()

repeat = 5;
maps.s0     = 1;
maps.d      = 11;
maps.links  = [ 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0;
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

maps(2).s0      = 1;
maps(2).d       = 11;
maps(2).links   = [ 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0;
                    0 0 1 0 0 0 0 0 0 0 0 0 0 0 0;
                    0 0 0 1 0 0 0 0 0 0 0 2 0 0 0;
                    0 0 0 0 1 0 0 0 0 0 0 0 0 0 0;
                    0 0 0 0 0 1 0 0 0 0 0 0 0 0 0;
                    0 0 0 0 0 0 1 0 0 0 0 0 0 0 0;
                    0 0 0 0 0 0 0 1 0 0 0 0 0 0 0;
                    0 0 0 0 0 0 0 0 1 0 0 0 0 0 0;
                    0 0 0 0 0 0 0 0 0 1 0 0 0 0 0;
                    0 0 0 0 0 0 0 0 0 0 1 0 0 0 0;
                    1 0 0 0 0 0 0 0 0 0 0 0 0 0 0;
                    0 0 0 0 0 0 0 0 0 0 0 0 1 0 0;
                    0 0 0 0 0 0 0 0 0 0 0 0 0 1 0;
                    0 0 0 0 0 0 0 0 0 0 0 0 0 0 1;
                    0 0 0 0 0 0 0 0 0 0 1 0 0 0 0];
                

maps(3) = complexmap();


traps = zeros(1,15);
traps(4) = 1;
traps(6) = 2;
traps(8) = 3;
maps(1).traps = traps;
maps(2).traps = traps;

complextraps = zeros(1,30);
complextraps(11) = 1;
complextraps(15) = 1;
complextraps(25) = 1;
complextraps(26) = 1;

maps(3).traps = complextraps;

stats = zeros(3,3, length(maps))

for m=1:size(maps,2)
    policy = 0;
    policy.p = ones(size(maps(m).traps));
    policy(2).p = ones(size(maps(m).traps))*2;
    policy(3).p = snake(maps(m));

    policy_stats = zeros(size(policy,2),3);


    for p=1:size(policy,2)
        plays=0;
        for i =1:repeat
            play = simul(maps(m),policy(p).p);
            plays = plays + play;
            if policy_stats(p,1) == 0 || policy_stats(p,1) > play
                policy_stats(p,1) = play;
            end
            if(policy_stats(p,2) < play)
                policy_stats(p,2) = play;
            end
        end
        policy_stats(p,3) = (plays) / repeat;
        stats(:,:,m) = policy_stats;
        %printf('maps:%d policy %d : %d %d %f \n',m,p,policy_stats(p,1), policy_stats(p,2), policy_stats(p,3));
    end
end
