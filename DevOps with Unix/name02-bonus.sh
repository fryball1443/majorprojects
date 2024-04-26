#/bin/bash
${TEMPDIR:=/tmp}
read -p "Enter your name: " name

tmpFile=$(mktemp "/tmp/$$.XXXX --suffix=name")
#namefile=$TEMPDIR/$$.name

#echo $namefile
#echo "$name" > $namefile
#cat "$namefile"

echo $tmpFile
echo "$name" > $tmpFile
cat "$tmpFile"

for ((i=999999999999999999999999999999999999999999999999999; i>0; i--))
do
  echo $i
done

cat > $namefile
rm $namefile