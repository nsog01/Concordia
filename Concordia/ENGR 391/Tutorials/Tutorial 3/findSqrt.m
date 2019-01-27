function xSol = findSqrt(p) 
    imax = 20;
    xPrev = p;
    relativeErr;
    FX = [1, -p];
    diffFX = diff(FX);
    


    for i = 1:imax
        xCurr = xPrev - (xPrev^2)/(2*xPrev);
        relativeErr = (xCurr - xPrev)/xCurr;
        
        if abs(relativeErr) <= 0.000001
            xSol = xCurr;
            break;
        elseif abs(relativeErr) > 0.000001
            break;
        end
        
        xPrev = xCurr;
    end    
end