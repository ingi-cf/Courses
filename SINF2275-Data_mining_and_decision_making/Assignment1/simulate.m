function stats = simulate()
policies = ["Security"; "Risky"; "Random";"Markov Dec"];
repeat = 500;
maps.s0     = 1;
maps.name   = "Map stop at d traps 1";
maps.d      = 11;
maps.links  = [ 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0;
                0 0 1 0 0 0 0 0 0 0  0 0 0 0 0;
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


maps(2).name   = "Map not stop at d traps 1";
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

traps2 = zeros(1,15);
traps2(2) = 1;
traps2(4) = 2;
traps2(6) = 1;
traps2(8) = 3;
traps2(12) = 2;

maps(4) = maps(1);
maps(5) = maps(2);
maps(4).name   = "Map stop at d traps 2";
maps(5).name   = "Map not stop at d traps 2";
maps(1).traps = traps;
maps(2).traps = traps;
maps(4).traps = traps2;
maps(5).traps = traps2;

maps(6) = maps(1);
maps(7) = maps(1);
maps(8) = maps(1);

maps(6).name = "Map with only type 1 traps";
maps(7).name = "Map with only type 2 traps";
maps(8).name = "Map with only type 3 traps";

traps3 = [0 0 1 0 1 0 1 0 1 0 0 1 0 0 0];
maps(6).traps = traps3;
maps(7).traps = traps3*2;
maps(8).traps = traps3*3;



complextraps = zeros(1,30);
complextraps(11) = 1;
complextraps(15) = 2;
complextraps(25) = 3;
complextraps(26) = 1;

maps(3).traps = complextraps;
maps(3).name   = "Complex Map";

stats = zeros(4,3, length(maps));

for m=1:size(maps,2)
    fid = fopen(strcat(["map" int2str(m) ".txt"]),"w");
    fid2 = fopen(strcat(["map" int2str(m) "-tex.txt"]),"w");
    fprintf(fid,'#-%s\n',maps(m).name);
    policy = 0;
    policy.p = ones(size(maps(m).traps));
    policy(2).p = ones(size(maps(m).traps))*2;
    policy(3).p = randomPolicy(maps(m).traps);
    policy(4).p = snake(maps(m));

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
        fprintf(fid,'%d %f %d %d \n',p,policy_stats(p,3),policy_stats(p,1), policy_stats(p,2));
        fprintf(fid2,'%s & %d & %d & %f \\\\\n',policies(p,:),policy_stats(p,1),policy_stats(p,2), policy_stats(p,3));
    end
    fclose(fid);
    fclose(fid2);
end
