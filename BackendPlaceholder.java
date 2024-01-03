import java.io.FileNotFoundException;

public class BackendPlaceholder implements BackendInterface{

  @Override
  public void readData(String fileName) throws FileNotFoundException {
    if (!fileName.equals("campus.dot")){
      throw new FileNotFoundException();
    }

  }

  @Override
  public Object shortestPath(String start, String destination) throws IllegalArgumentException {
    return null;
  }

  @Override
  public String toString(){
    return "5 buildings, 6 routes";
  }
}


