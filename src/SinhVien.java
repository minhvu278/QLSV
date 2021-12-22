import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class SinhVien {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String currentPath = Paths.get("").toAbsolutePath().toString();
            Path path = Paths.get(currentPath + "/src/Data.txt");

            List<String> dataListSv = Files.readAllLines(path);

            while (true) {
                System.out.println("**          MENU          **");
                System.out.println("1.In danh sach");
                System.out.println("2.Them sinh vien");
                System.out.println("3.Sua sinh vien");
                System.out.println("4.Xoa sinh vien");
                System.out.println("5.Tim kiem sinh vien");
                System.out.println("6.Load file");
                System.out.println("7.Luu file");
                System.out.println("8.Thoat chuong trinh");
                System.out.print("Nhap vao lua chon cua ban: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        String tableHeader = String.format("%-15s | %-5s | %-15s\n",
                                "Ho Ten",
                                "Tuoi",
                                "Diem"
                        );

                        boolean found = false;
                        StringBuilder foundStr = new StringBuilder();
                        for (String listSv : dataListSv) {
                            String[] data = listSv.split(";");
                            ThongTin thongTin = new ThongTin();
                            thongTin.hoTen = data[0];
                            thongTin.tuoi = Integer.parseInt(data[1]);
                            thongTin.diem = Double.parseDouble(data[2]);

                                foundStr.append(String.format("%-15s | %-5s | %-15s\n",
                                        thongTin.hoTen,
                                        thongTin.tuoi,
                                        thongTin.diem
                                ));
                                found = true;
                        }
                        if (!found) {
                            System.out.println("Khong co sinh vien nao trong danh sach");
                        } else {
                            System.out.print(tableHeader);
                            System.out.print(foundStr.toString());
                        }
                        System.out.println("Go phim bat ki de tiep tuc");
                        scanner.nextLine();
                        scanner.nextLine();
                        break;
                    case 2:
                        scanner.nextLine();
                        System.out.print("Nhap vao ten: ");
                        scanner.nextLine();
                        System.out.print("Nhap vao tuoi: ");
                        scanner.nextLine();
                        System.out.print("Nhap vao diem: ");
                        scanner.nextLine();


                        break;
                    case 5:
                        System.out.print("Nhap vao sinh vien muon tim kiem: ");
                        scanner.nextLine();
                        String search = scanner.nextLine();

                        String tableHeader1 = String.format("%-15s | %-5s | %-15s\n",
                                "Ho Ten",
                                "Tuoi",
                                "Diem"
                        );

                        boolean found1 = false;
                        StringBuilder foundStr1 = new StringBuilder();
                        for (String listSv : dataListSv) {
                            String[] data = listSv.split(";");
                            ThongTin thongTin = new ThongTin();
                            thongTin.hoTen = data[0];
                            thongTin.tuoi = Integer.parseInt(data[1]);
                            thongTin.diem = Double.parseDouble(data[2]);

                            if (thongTin.hoTen.contains(search)) {
                                foundStr1.append(String.format("%-15s | %-5s | %-15s\n",
                                        thongTin.hoTen,
                                        thongTin.tuoi,
                                        thongTin.diem
                                ));
                                found1 = true;
                            }
                        }
                        if (!found1) {
                            System.out.println("Khong co sinh vien nao trong danh sach");
                        } else {
                            System.out.print(tableHeader1);
                            System.out.print(foundStr1.toString());
                        }
                        System.out.println("Go phim bat ki de tiep tuc");
                        scanner.nextLine();
                        break;
                    case 8:
                        System.out.println("Cam on ban da su dung chuong trinh");
                        return;
                }



                System.out.println("-------------------------------------------------------\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
