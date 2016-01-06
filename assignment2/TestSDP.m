function M = TestSDP;

files = dir('Test/max*');
M= [];
for file = files';
metaData = strsplit(file.name, '_');
nodes = str2num(cell2mat(metaData(2)));
edgePro = str2double(cell2mat(metaData(3)))/10;
mWeight =str2num(cell2mat(metaData(4)));
Ts = [ 1 5 10 25 50 100];
    for t = Ts
    [LB,UB, elapsed] = readSdp(file.name,t);
    z = [edgePro,mWeight,t, nodes,LB,UB, elapsed];
    M = [M; z];
    end
end


%list of file names




