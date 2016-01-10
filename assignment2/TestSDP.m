function M = TestSDP;

%this function collects the test data (LB, UB, runtime) for graphs in the 'output' folder
files = dir('generator/output/max*');
M= [];
for file = files';
    metaData = strsplit(file.name, '_');
    nodes = str2num(cell2mat(metaData(2)));
    edgePro = str2double(cell2mat(metaData(3)))/10;
    mWeight =str2num(cell2mat(metaData(4)));

    fprintf('reading sdp with filename: %s\n', file.name);
    lines = readSdp(strcat('generator/output/',file.name), M);
    Ts = [1 5 10 25 50 75 100]; %number of trials

    i = 1;
    for t = Ts
        lines(i,:)
        
        ph = lines(i,:);
        LB = ph(1);
        UB = ph(2);
        elapsed = ph(3);
        
        z = [edgePro,mWeight,t, nodes,LB,UB, elapsed];
        M = [M; z];
        i = i+1;
    end
    
end

%write test data to a file
csvwrite('csvlist.dat',M)






