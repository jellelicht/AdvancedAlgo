function [M, names] = TestSDP;

files = dir('Test/*.txt');
M= [];
for file = files';
[LB,UB, elapsed] = readSdp(file.name);
z = [LB,UB, elapsed];
M = [M; z];
end


%list of file names
names = files.name;



