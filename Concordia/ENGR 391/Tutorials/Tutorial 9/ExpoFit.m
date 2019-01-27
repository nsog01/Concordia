function [b, m] = ExpoFit(x, y)
    nx = length(x);
    ny = length(y);
    
    if nx ~= ny
        disp('Error: x and y points are not the same magnitude');
        b = 'Error';
        m = 'Error';
    else
        Y = log(y);
        X = x;
        N = nx;
        
        sumX = sum(X);
        sumY = sum(Y);
        
        % The dot operator (i.e. '.') maps the following operator 
        % (i.e. in this case '*') from every element of X to every 
        % corresponding element of Y
        sumXY = sum(X.*Y);
        sumXX = sum(X.^2);
        
        a1 = (N*sumXY - sumX*sumY)/(N*sumXX - sumX^2);
        a0 = (sumY/N) - a1*(sumX/N);
        b = exp(a0);
        m = a1;
    end
end