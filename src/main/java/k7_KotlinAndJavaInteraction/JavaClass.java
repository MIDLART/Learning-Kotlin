package k7_KotlinAndJavaInteraction;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JavaClass {
  private String model;

  public JavaClass(String model) {
    this.model = model;
  }

  public int add(int a, int b) {
    return a + b;
  }

  public static String getVersion() {
    return "2.0";
  }

  // Nullable метод
  public String findNameById(int id) {
    return id == 1 ? "John" : null;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }


  // Null-безопасность
  @NotNull
  public String name = "meow";

  @Nullable
  public String email = null;
}
